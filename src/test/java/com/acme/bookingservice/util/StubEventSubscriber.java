package com.acme.bookingservice.util;

import com.acme.bookingservice.domain.query.EventSubscriber;

import java.util.function.BiFunction;

public class StubEventSubscriber implements EventSubscriber {

    @Override
    public void subscribe(String eventType, BiFunction<String, String, Void> fn) {

    }

}
