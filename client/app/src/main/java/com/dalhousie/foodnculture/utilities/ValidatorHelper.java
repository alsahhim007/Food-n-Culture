package com.dalhousie.foodnculture.utilities;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorHelper {

    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

}
