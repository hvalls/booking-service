package com.acme.bookingservice.domain.command;

import com.acme.bookingservice.domain.common.Id;

public record CreateBookingCommand(Id resourceId) {
}
