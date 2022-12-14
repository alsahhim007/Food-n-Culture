package com.dalhousie.foodnculture.exceptions;

public class UserAlreadyExist extends RuntimeException {

    public UserAlreadyExist(){
        super();
    }

    public UserAlreadyExist(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExist(String message) {
        super(message);
    }

    public UserAlreadyExist(Throwable cause) {
        super(cause);
    }
}
