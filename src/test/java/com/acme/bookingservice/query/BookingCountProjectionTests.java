package com.acme.bookingservice.query;

import com.acme.bookingservice.adapter.eventbus.InMemoryEventPublisherSubscriber;
import com.acme.bookingservice.domain.query.BookingCountProjection;
import com.acme.bookingservice.util.MockDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingCountProjectionTests {

    @Test
    public void shouldDelegateGetBookingCountToDao() {
        var dao = new MockDao();
        var projection = new BookingCountProjection(dao, new InMemoryEventPublisherSubscriber());

        projection.get();

        assertTrue(dao.getBookingCountCalled);
    }

}
