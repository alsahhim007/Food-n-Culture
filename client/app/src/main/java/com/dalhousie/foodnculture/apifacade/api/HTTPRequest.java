package com.dalhousie.foodnculture.apifacade.api;

import com.dalhousie.foodnculture.apifacade.contract.IRequest;
import com.dalhousie.foodnculture.utilities.HTTPHelper;

/*
    Singleton class to handle all HTTP Request
 */
public class HTTPRequest<T> implements IRequest<T> {

    private static HTTPRequest request;

    private HTTPRequest() {
    }

    public static HTTPRequest getInstance() {
        if(request == null) {
            request = new HTTPRequest();
        }
        return request;
    }

    @Override
    public StringBuffer doGet(String url) throws Exception {
        return HTTPHelper.performHttpRequest(url, "GET");
    }

    @Override
    public StringBuffer doPost(String url, String jsonData) throws Exception {
        return HTTPHelper.performHttpRequest(url, "POST", jsonData);
    }

    @Override
    public StringBuffer doPut(String url, String jsonData) throws Exception {
        return HTTPHelper.performHttpRequest(url, "PUT", jsonData);
    }

    @Override
    public StringBuffer doDelete(String url) throws Exception {
        return HTTPHelper.performHttpRequest(url, "DELETE");
    }
}
