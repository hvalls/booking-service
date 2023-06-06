package com.acme.bookingservice.domain.command.event;

import com.acme.bookingservice.adapter.eventbus.EventBus;
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
        System.out.println("dispatching event..." + ev);
        //TODO: Publish to event bus
    }

}
