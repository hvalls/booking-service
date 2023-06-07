package com.acme.bookingservice;

import com.acme.bookingservice.domain.command.BookingAlreadyExistsException;
import com.acme.bookingservice.domain.command.CreateBookingCommand;
import com.acme.bookingservice.domain.command.CreateBookingCommandHandler;
import com.acme.bookingservice.domain.common.Id;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Of course, in a real scenario, we would use some tool like Mockito or similar
 * to write more accurate test cases, e.g. check if one method have been called with the expected parameters.
 */
class CreateBookingTests {

    @Test
    public void shouldThrowWhenBookingAlreadyExists() {
        var bookingId = Id.random();
        var resourceId = Id.random();
        var cmd = new CreateBookingCommand(bookingId, resourceId);
        CreateBookingCommandHandler handler = new CreateBookingCommandHandler(
                new MockBookingRepository(booking -> null, id -> true),
                new StubEventPublisher()
        );

        assertThrows(BookingAlreadyExistsException.class, () -> handler.handle(cmd));
    }

    @Test
    public void shouldStoreBooking() throws BookingAlreadyExistsException {
        var bookingId = Id.random();
        var resourceId = Id.random();
        var cmd = new CreateBookingCommand(bookingId, resourceId);
        var repository = new MockBookingRepository(booking -> null, id -> false);
        CreateBookingCommandHandler handler = new CreateBookingCommandHandler(repository, new StubEventPublisher());

        handler.handle(cmd);

        assertTrue(repository.addCalled);
    }

    @Test
    public void shoulPublishEvent() throws BookingAlreadyExistsException {
        var bookingId = Id.random();
        var resourceId = Id.random();
        var cmd = new CreateBookingCommand(bookingId, resourceId);
        var eventPublisher = new StubEventPublisher();
        CreateBookingCommandHandler handler = new CreateBookingCommandHandler(
                new MockBookingRepository(booking -> null, id -> false),
                eventPublisher
        );

        handler.handle(cmd);

        assertTrue(eventPublisher.publishCalled);
    }

    @Test
    public void shoulReturnBookingId() throws BookingAlreadyExistsException {
        var bookingId = Id.random();
        var resourceId = Id.random();
        var cmd = new CreateBookingCommand(bookingId, resourceId);
        CreateBookingCommandHandler handler = new CreateBookingCommandHandler(
                new MockBookingRepository(booking -> null, id -> false),
                new StubEventPublisher()
        );

        var resultBookingId = handler.handle(cmd);

        assertEquals(bookingId, resultBookingId);
    }

}
