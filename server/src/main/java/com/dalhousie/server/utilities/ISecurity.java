package com.dalhousie.server.utilities;

public interface ISecurity {
    public String encrypt(String text);
    public String decrypt(String text);
}

