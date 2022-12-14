package com.dalhousie.foodnculture.utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidatorHelperTest {

    @Test
    public void validateValidEmail() {
        assertTrue(ValidatorHelper.isValidEmail("sumit.kumar@dal.ca"));
    }

    @Test
    public void validateInvalidEmail() {
        assertFalse(ValidatorHelper.isValidEmail("test.check"));
    }

    @Test
    public void validateValidPhone() {
        assertTrue(ValidatorHelper.isValidPhone("782-882-4339"));
    }

    @Test
    public void validateInvalidPhone() {
        assertFalse(ValidatorHelper.isValidPhone("4339"));
    }

}
