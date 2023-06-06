package com.acme.bookingservice.domain.common.event;

import com.acme.bookingservice.domain.common.Id;

public interface DomainEvent {

    Id eventId();

    String topic();

    String jsonPayload();

}
