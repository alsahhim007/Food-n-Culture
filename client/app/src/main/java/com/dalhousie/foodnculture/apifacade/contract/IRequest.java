package com.dalhousie.foodnculture.apifacade.contract;

public interface IRequest<T> {
    StringBuffer doGet(String url) throws Exception;

    StringBuffer doPost(String url, String jsonData) throws Exception;

    StringBuffer doPut(String url, String jsonData) throws Exception;

    StringBuffer doDelete(String url) throws Exception;
}
