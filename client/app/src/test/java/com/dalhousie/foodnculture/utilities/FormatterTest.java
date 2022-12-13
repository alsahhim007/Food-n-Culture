package com.dalhousie.foodnculture.utilities;

import static org.junit.Assert.assertEquals;

import com.dalhousie.foodnculture.models.Event;

import org.junit.Test;

public class FormatterTest {

    @Test
    public void formatDateAndVenueTest() {
        Formatter formatter = new Formatter();
        Event event = new Event();
        event.setStartDatetime("2022-12-13 10:00:00");
        event.setVenue("Downtown Halifax");
        assertEquals(formatter.formatDateAndVenue(event), "13 DEC @ 10:0 | Downtown Halifax");
    }
}
