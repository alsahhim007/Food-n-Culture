package com.dalhousie.foodnculture.apifacade;

import android.os.StrictMode;

public class ApiFacade {

    private static ApiFacade apiFacade;
    private final IRequest request;
    private final IUserOperation userOperation;

    private ApiFacade(IRequest request) {
        this.request = request;
        this.userOperation = new UsersApi(this.request);
    }

    public static ApiFacade getInstance() {
        if( apiFacade == null) {
            apiFacade = new ApiFacade(HTTPRequest.getInstance());
        }
        return apiFacade;
    }

    public IUserOperation getUserApi() {
        return this.userOperation;
    }

}
