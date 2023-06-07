package com.acme.bookingservice.adapter.eventbus;

import com.acme.bookingservice.domain.command.EventPublisher;
import com.acme.bookingservice.domain.query.EventSubscriber;

import java.util.*;
import java.util.function.BiFunction;

public class InMemoryEventPublisherSubscriber implements EventPublisher, EventSubscriber {

    private final Map<String, List<BiFunction<String, String, Void>>> subscribers = new HashMap<>();
    private final ConsumedEventsCache consumedEvents = new ConsumedEventsCache();

    @Override
    public void publish(String eventType, String eventId, String jsonPayload) {
        var eventSubscribers = subscribers.getOrDefault(eventType, Collections.emptyList());
        eventSubscribers.forEach(sub -> sub.apply(eventId, jsonPayload));
    }

    @Override
    public void subscribe(String eventType, BiFunction<String, String, Void> fn) {
        subscribers.computeIfAbsent(eventType, m -> new LinkedList<>())
                .add((eventId, jsonPayload) -> processEvent(eventId, jsonPayload, fn));
    }

    private Void processEvent(String eventId, String jsonPayload, BiFunction<String, String, Void> fn) {
        if (consumedEvents.isConsumed(eventId)) {
            return null;
        }
        consumedEvents.markAsConsumed(eventId);
        return fn.apply(eventId, jsonPayload);
    }

}
