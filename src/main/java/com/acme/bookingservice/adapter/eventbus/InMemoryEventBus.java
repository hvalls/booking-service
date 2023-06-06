package com.acme.bookingservice.adapter.eventbus;

import com.acme.bookingservice.domain.common.event.EventBus;

import java.util.*;
import java.util.function.Function;

public class InMemoryEventBus implements EventBus {

    private final Map<String, List<Function<String, Void>>> subscribers = new HashMap<>();

    @Override
    public void publish(String topic, String jsonPayload) {
        var eventSubscribers = subscribers.getOrDefault(topic, Collections.emptyList());
        eventSubscribers.forEach(sub -> sub.apply(jsonPayload));
    }

    @Override
    public void subscribe(String topic, Function<String, Void> fn) {
        subscribers.computeIfAbsent(topic, m -> new LinkedList<>()).add(fn);
    }

}
