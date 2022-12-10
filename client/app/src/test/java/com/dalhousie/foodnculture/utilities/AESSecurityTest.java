package com.dalhousie.foodnculture.utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AESSecurityTest {

    @Test
    public void encryptTest() {
        assertEquals(AESSecurity.encrypt("Test@123"), "PjmQhOFQZpVXFhVEv4xuVg==");
    }

    @Test
    public void decryptTest() {
        assertEquals(AESSecurity.decrypt("PjmQhOFQZpVXFhVEv4xuVg=="), "Test@123");
    }
}
