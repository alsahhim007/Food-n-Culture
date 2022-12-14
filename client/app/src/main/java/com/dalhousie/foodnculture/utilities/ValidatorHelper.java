package com.dalhousie.foodnculture.utilities;

import androidx.core.util.PatternsCompat;

import java.util.regex.Pattern;

public class ValidatorHelper {

    public static boolean isValidEmail(String email) {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        Pattern pattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
        return pattern.matcher(phone).matches();
    }

}
