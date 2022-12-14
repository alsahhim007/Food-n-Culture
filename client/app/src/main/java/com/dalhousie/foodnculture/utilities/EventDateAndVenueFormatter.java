package com.dalhousie.foodnculture.utilities;

import com.dalhousie.foodnculture.models.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventDateAndVenueFormatter implements IFormatter<Event> {

    @Override
    public String format(Event event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(event.getStartDatetime(), formatter);
        return String.format("%s %s @ %s:%s | %s", dateTime.getDayOfMonth(),
                dateTime.getMonth().toString().substring(0,3),
                dateTime.getHour(), dateTime.getMinute(), event.getVenue());
    }

}
