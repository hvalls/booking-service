package com.acme.bookingservice.adapter.event;

import org.springframework.util.ConcurrentLruCache;

/**
 * I decided to use an LRU cache for storing consumed events (IDs) and avoid duplicated.
 * Proper messaging systems (kafka, rabbitmq...) already implement this complex feature.
 * This implementation would not work in the case where an old eventId is emitted again,
 * after flush it from the LRU cache. However, personally, I consider it is good enough for
 * this case. We can discuss more about this interesting topic in the interview.
 */
public class ConsumedEventsCache {

    private static final int CONSUMED_EVENTS_IDS_CACHE_CAPACITY = 1000;
    private final ConcurrentLruCache<String, Boolean> consumedEventIds =
            new ConcurrentLruCache<>(CONSUMED_EVENTS_IDS_CACHE_CAPACITY, s -> true);


    public boolean isConsumed(String eventId) {
        return consumedEventIds.contains(eventId);
    }

    public void markAsConsumed(String eventId) {
        consumedEventIds.get(eventId);
    }

}
