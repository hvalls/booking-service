package com.acme.bookingservice.domain.common;

public interface DomainEvent {

    Id eventId();

    String eventType();

    String asJson();

}
