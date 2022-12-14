package com.dalhousie.foodnculture.utilities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESSecurity implements ISecurity {

    private static final String SECRET_KEY = "RHAWKEY"; //TODO? please find me a better place
    private static final String ALGORITHM = "AES";
    private static byte[] key;
    private static SecretKey secret;

    public void getSecretKey(String secretKey) {
        MessageDigest mdigest = null;
        try {
            key = secretKey.getBytes(StandardCharsets.UTF_8);
            mdigest = MessageDigest.getInstance("SHA-1");
            key = mdigest.digest(key);
            key = Arrays.copyOf(key, 16);
            secret = new SecretKeySpec(key, ALGORITHM);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String encrypt(String text) {
        try {
            getSecretKey(SECRET_KEY);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes("UTF-8")));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String decrypt(String text) {
        try {
            getSecretKey(SECRET_KEY);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(Base64.getDecoder().decode(text)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
