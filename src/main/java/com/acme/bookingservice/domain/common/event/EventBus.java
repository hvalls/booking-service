package com.acme.bookingservice.domain.common.event;

import java.util.function.BiFunction;

public interface EventBus {

    void publish(String topic, String eventId, String jsonPayload);

    /**
     * @param topic
     * @param fn    function with no return value and 2 arguments: eventId and jsonPayload
     */
    void subscribe(String topic, BiFunction<String, String, Void> fn);

}
