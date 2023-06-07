package com.acme.bookingservice.integration;

import com.acme.bookingservice.adapter.eventbus.InMemoryEventPublisherSubscriber;
import com.acme.bookingservice.domain.command.BookingAlreadyExistsException;
import com.acme.bookingservice.domain.command.CreateBookingCommand;
import com.acme.bookingservice.domain.command.CreateBookingCommandHandler;
import com.acme.bookingservice.domain.common.Id;
import com.acme.bookingservice.domain.query.BookingCountProjection;
import com.acme.bookingservice.util.MockBookingRepository;
import com.acme.bookingservice.util.MockDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTests {

    @Test
    public void bookingCountProjectionShouldIncrementCountWhenBookingConfirmedEventIsEmitted() throws BookingAlreadyExistsException {
        // Given
        var pubSub = new InMemoryEventPublisherSubscriber();
        var dao = new MockDao();
        var repository = new MockBookingRepository(booking -> null, id -> false);
        var handler = new CreateBookingCommandHandler(repository, pubSub);
        new BookingCountProjection(dao, pubSub);

        // When
        handler.handle(new CreateBookingCommand(Id.random(), Id.random()));

        // Then
        assertTrue(dao.incrementBookingCountCalled);
    }

}
