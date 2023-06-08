package com.acme.bookingservice.adapter.http;

import com.acme.bookingservice.domain.command.BookingAlreadyExistsException;
import com.acme.bookingservice.domain.command.CommandGateway;
import com.acme.bookingservice.domain.query.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

@Controller
public class BookingController {

    @Autowired
    CommandGateway commandGateway;

    @Autowired
    QueryGateway queryGateway;

    @PostMapping(value = "/bookings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBooking(@RequestBody CreateBookingRequestDto dto) {
        try {
            var bookingId = commandGateway.createBooking(dto.resourceId());
            return ResponseEntity.created(
                    URI.create(String.format("/bookings/%s", bookingId.value()))).build();
        } catch (BookingAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/bookings/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBookingCountResponseDto> getBookingCount() {
        var count = queryGateway.getBookingCount();
        return ResponseEntity.ok(new GetBookingCountResponseDto(count));
    }

}
