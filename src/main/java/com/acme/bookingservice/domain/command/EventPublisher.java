package com.acme.bookingservice.domain.command;

public interface EventPublisher {

    void publish(String eventType, String eventId, String jsonPayload);

}
