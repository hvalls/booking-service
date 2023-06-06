package com.acme.bookingservice.domain.query;

import com.acme.bookingservice.domain.common.event.BookingConfirmed;
import com.acme.bookingservice.domain.common.event.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    @Autowired
    public EventHandler(EventBus eventBus, BookingCountProjection bookingCountProjection) {
        eventBus.subscribe(BookingConfirmed.TOPIC, ev ->
                bookingCountProjection.project(BookingConfirmed.fromJsonPayload(ev)));
    }

}
