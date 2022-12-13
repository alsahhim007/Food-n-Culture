package com.dalhousie.foodnculture.utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AESSecurityTest {

    @Test
    public void encryptTest() {
        ISecurity security = new AESSecurity();
        assertEquals(security.encrypt("Test@123"), "PjmQhOFQZpVXFhVEv4xuVg==");
    }

    @Test
    public void decryptTest() {
        ISecurity security = new AESSecurity();
        assertEquals(security.decrypt("PjmQhOFQZpVXFhVEv4xuVg=="), "Test@123");
    }
}
