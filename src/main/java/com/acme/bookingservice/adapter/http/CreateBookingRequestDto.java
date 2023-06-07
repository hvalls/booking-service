package com.acme.bookingservice.adapter.http;

/**
 *
 * @param resourceId: In this context, a resource represents any bookable element
 */
public record CreateBookingRequestDto(String resourceId) {
}
