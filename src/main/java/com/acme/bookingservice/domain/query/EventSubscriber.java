package com.acme.bookingservice.domain.query;

import java.util.function.BiFunction;

public interface EventSubscriber {

    /**
     *
     * @param eventType
     * @param fn "callback" function that is executed when an event of eventType is emitted.
     * fn function receives the eventId and the jsonPayload and returns nothing.
     */
    void subscribe(String eventType, BiFunction<String, String, Void> fn);

}
