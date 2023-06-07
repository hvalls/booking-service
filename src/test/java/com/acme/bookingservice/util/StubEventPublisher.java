package com.acme.bookingservice.util;

import com.acme.bookingservice.domain.command.EventPublisher;

public class StubEventPublisher implements EventPublisher {

    public boolean publishCalled = false;

    @Override
    public void publish(String eventType, String eventId, String jsonPayload) {
        publishCalled = true;
    }

}
