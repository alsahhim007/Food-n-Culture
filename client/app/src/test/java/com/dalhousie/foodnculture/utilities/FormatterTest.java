package com.dalhousie.foodnculture.utilities;

import static org.junit.Assert.assertEquals;

import com.dalhousie.foodnculture.models.Event;

import org.junit.Test;

public class FormatterTest {

    @Test
    public void formatDateAndVenueTest() {
        IFormatter formatter = new EventDateAndVenueFormatter();
        Event event = new Event();
        event.setStartDatetime("2022-12-13 10:00:00");
        event.setVenue("Downtown Halifax");
        assertEquals(formatter.format(event), "13 DEC @ 10:0 | Downtown Halifax");
    }
}
