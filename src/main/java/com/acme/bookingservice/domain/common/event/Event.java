package com.acme.bookingservice.domain.common.event;

public interface Event {

    String topic();

    String jsonPayload();

}
