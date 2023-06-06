package com.acme.bookingservice.domain.command.event;

import com.acme.bookingservice.domain.common.event.BookingConfirmed;
import com.acme.bookingservice.domain.common.event.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

    private final EventBus eventBus;

    @Autowired
    public EventDispatcher(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void dispatch(BookingConfirmed ev) {
        eventBus.publish(ev.topic(), ev.jsonPayload());
    }

}
