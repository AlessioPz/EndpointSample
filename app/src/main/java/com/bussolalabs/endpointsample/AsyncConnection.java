package com.bussolalabs.endpointsample;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.bussolalabs.endpointsample.backend.suggestionBeanApi.SuggestionBeanApi;
import com.bussolalabs.endpointsample.backend.suggestionBeanApi.model.SuggestionBean;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by alessio on 10/11/15.
 */
public class AsyncConnection extends AsyncTask<Context, String, Boolean> {

    Context context = null;

    private static final String ENDPOINT_APP_NAME = "<your project ID>";
    public static final String URL_ENDPOINT_PROD = "https://" + ENDPOINT_APP_NAME+".appspot.com/_ah/api/";
    public static final String URL_ENDPOINT_PROD_V2 = "https://2-dot-" + ENDPOINT_APP_NAME+".appspot.com/_ah/api/";
    public static final String URL_ENDPOINT_SVIL = "http://10.0.2.2:8080/_ah/api/";

    @Override
    protected Boolean doInBackground(Context... params) {
        context = params[0];

        SuggestionBeanApi.Builder suggestionBuilder = new SuggestionBeanApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setApplicationName("<your project name>")
                .setRootUrl(URL_ENDPOINT_PROD) // <-- select the right ENDPOINT URL
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                                                       @Override
                                                       public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                                           abstractGoogleClientRequest.setDisableGZipContent(false);
                                                       }
                                                   }
                );
        SuggestionBeanApi suggestionBeanApiService = suggestionBuilder.build();
        try {
            SuggestionBean suggestionBean = suggestionBeanApiService.getAnswer(((MainActivity) context).question).execute();
            ((MainActivity)context).answer = suggestionBean.getAnswer();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            // manage connection error
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean res) {
        super.onPostExecute(res);

        if (!res) {
            Toast.makeText(context, "Error connection", Toast.LENGTH_LONG).show();
        }
    }
}
