package com.acme.bookingservice.domain.command.event;

public record BookingConfirmed(String bookingId, String resourceId, int eventVersion) {

    /**
     * Use for future backwards compat in case of event payload structure changes
     */
    public static final int EVENT_VERSION = 1;

}
