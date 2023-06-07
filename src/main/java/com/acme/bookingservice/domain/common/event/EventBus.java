package com.acme.bookingservice.domain.common.event;

import java.util.function.BiFunction;

public interface EventBus {

    void publish(String eventType, String eventId, String jsonPayload);

    /**
     * @param eventType
     * @param fn    function with no return value and 2 arguments: eventId and jsonPayload
     */
    void subscribe(String eventType, BiFunction<String, String, Void> fn);

}
