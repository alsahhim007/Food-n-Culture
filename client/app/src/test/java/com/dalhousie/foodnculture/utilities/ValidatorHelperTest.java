package com.dalhousie.foodnculture.utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ValidatorHelperTest {

    @Test
    public void validateValidEmail()
    {
        assertEquals(true, ValidatorHelper.isValidEmail("sumit.kumar@dal.ca"));
    }

    @Test
    public void validateInvalidEmail()
    {
        assertEquals(false, ValidatorHelper.isValidEmail("test.check"));
    }

    @Test
    public void validateValidPhone()
    {
        assertEquals(true, ValidatorHelper.isValidPhone("782-882-4339"));
    }

    @Test
    public void validateInvalidPhone()
    {
        assertEquals(false, ValidatorHelper.isValidPhone("4339"));
    }

}
