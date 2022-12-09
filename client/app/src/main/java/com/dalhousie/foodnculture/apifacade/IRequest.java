package com.dalhousie.foodnculture.apifacade;

public interface IRequest<T> {
    public StringBuffer doGet(String url) throws Exception;
    public StringBuffer doPost(String url, String jsonData) throws Exception;
    public StringBuffer doPut(String url, String jsonData) throws Exception;
    public StringBuffer doDelete(String url) throws Exception;
}
