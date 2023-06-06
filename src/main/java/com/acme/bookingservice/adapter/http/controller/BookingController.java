package com.acme.bookingservice.adapter.http.controller;

import com.acme.bookingservice.adapter.http.dto.CreateBookingRequestDto;
import com.acme.bookingservice.domain.command.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

@Controller
public class BookingController {

    @Autowired
    CommandGateway commandGateway;

    @PostMapping(value = "/bookings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createBooking(@RequestBody CreateBookingRequestDto dto) {
        // TODO: Handle errors
        commandGateway.createBooking(dto.resourceId());
        return ResponseEntity.created(URI.create("")).build();
    }

}
