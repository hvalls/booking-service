package com.acme.bookingservice.domain.common.event;

import java.util.function.Function;

public interface EventBus {

    void publish(String topic, String jsonPayload);

    void subscribe(String topic, Function<String, Void> fn);

}
