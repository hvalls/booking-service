package com.acme.bookingservice.domain.common.event;

import com.acme.bookingservice.domain.command.Booking;
import com.acme.bookingservice.domain.common.Id;

public class BookingConfirmed implements DomainEvent {

    public static final int EVENT_VERSION = 1;

    public static final String EVENT_TYPE = "booking.confirmed";

    private final Id eventId;
    private final Id bookingId;
    private final Id resourceId;
    private final int eventVersion;

    /**
     * In case of event payload structure changes, keeping track of the event version
     * can be really useful for backwards compatibility
     */

    public static BookingConfirmed fromJsonPayload(String jsonPayload) {
        return null;
    }

    public BookingConfirmed(Id eventId, Id bookingId, Id resourceId, int eventVersion) {
        this.eventId = eventId;
        this.bookingId = bookingId;
        this.resourceId = resourceId;
        this.eventVersion = eventVersion;
    }

    public BookingConfirmed(Booking booking) {
        this(Id.random(), booking.id, booking.getResourceId(), EVENT_VERSION);
    }

    @Override
    public Id eventId() {
        return eventId;
    }

    @Override
    public String type() {
        return EVENT_TYPE;
    }

    /**
     * Although events are sent through in-process memory, I decided to serialize
     * events as JSON so, in the future, event bus implementation could be switched to a
     * distributed one (kafka, rabbitmq...) easily.
     * <p>
     * Also, events payload schema should be documented using tools like AsyncAPI, since
     * it could be considered part of the API as well.
     * <p>
     * NOTE: Of course, a JSON manipulation library could (should?) be used, but I decided not including
     * one more dependency to the project
     */
    @Override
    public String jsonPayload() {
        return String.format("""
                          {
                            "eventType": "%s",
                            "eventId": "%s",
                            "bookingId" : "%s",
                            "resourceId": "%s",
                            "eventVersion": %d
                          }
                        """,
                EVENT_TYPE, eventId, bookingId, resourceId, eventVersion);
    }

}
