package com.acme.bookingservice.domain.common.event;

public record BookingConfirmed(String bookingId, String resourceId, int eventVersion) implements Event {

    /**
     * In case of event payload structure changes, keeping track of the event version
     * can be really useful for backwards compatibility
     */
    public static final int EVENT_VERSION = 1;

    public static final String TOPIC = "booking.confirmed";

    @Override
    public String topic() {
        return TOPIC;
    }

    /**
     *
     * Although events are sent through in-process memory, I decided to serialize
     * events as JSON so, in the future, event bus implementation could be switched to a
     * distributed one (kafka, rabbitmq...) easily.
     *
     * Also, events payload schema should be documented using tools like AsyncAPI, since
     * it could be considered part of the API as well.
     *
     * NOTE: Of course, a JSON manipulation library could (should?) be used, but I decided not including
     * one more dependency to the project
     */
    @Override
    public String jsonPayload() {
        return String.format("""
          {
            "bookingId" : "%s",
            "resourceId": "%s",
            "eventVersion": %d
          }
        """,
        bookingId, resourceId, eventVersion);
    }

}
