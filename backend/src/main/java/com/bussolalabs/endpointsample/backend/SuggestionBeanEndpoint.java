package com.bussolalabs.endpointsample.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "suggestionBeanApi",
        version = "v1",
        resource = "suggestionBean",
        namespace = @ApiNamespace(
                ownerDomain = "backend.endpointsample.bussolalabs.com",
                ownerName = "backend.endpointsample.bussolalabs.com",
                packagePath = ""
        )
)
public class SuggestionBeanEndpoint {

    private static final Logger logger = Logger.getLogger(SuggestionBeanEndpoint.class.getName());

    /**
     * This method gets the <code>SuggestionBean</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>SuggestionBean</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getSuggestionBean")
    public SuggestionBean getSuggestionBean(@Named("id") String id) {
        // TODO: Implement this function
        logger.info("Calling getSuggestionBean method");
        return new SuggestionBean();
    }

    /**
     * This inserts a new <code>SuggestionBean</code> object.
     *
     * @param suggestionBean The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertSuggestionBean")
    public SuggestionBean insertSuggestionBean(SuggestionBean suggestionBean) {
        // TODO: Implement this function
        logger.info("Calling insertSuggestionBean method");
        return suggestionBean;
    }

    /**
     *
     * @param question The user asks
     * @return The object with the answer
     */
    @ApiMethod(name = "getAnswer", path = "giveMeAnAnswer")
    public SuggestionBean getAnswer(@Named("question") String question) {
        logger.info("Calling giveMeAnAnswer with question = " + question);
        SuggestionBean suggestionBean = new SuggestionBean();
        suggestionBean.setAnswer("Drink water! (your question was: " + question + ")");
        return suggestionBean;
    }
}