package com.acme.bookingservice.domain.query;

import com.acme.bookingservice.domain.common.BookingConfirmed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingCountProjection {

    private final DAO dao;

    @Autowired
    public BookingCountProjection(DAO dao, EventSubscriber subscriber) {
        this.dao = dao;
        subscriber.subscribe(BookingConfirmed.EVENT_TYPE, this::onBookingConfirmed);
    }

    /**
     * @return current computed count
     */
    public int get() {
        return dao.getBookingCount();
    }

    private Void onBookingConfirmed(String eventId, String jsonPayload) {
        // We don't need to deserialize the event payload because we are just increasing the counter.
        // In the case we want to make it more resilient, when the process starts, we should read all
        // the events of type BookingConfirmed and start counting from that point (event sourcing), because
        // there could be unread events. For that, we should store the events in the event bus like kafka
        // or rabbitmq do.
        // For simplicity, I decided not to do it in this case because publisher and subscriber are running
        // in the same process. We can discuss this in the interview.
        dao.incrementBookingCount();
        return null;
    }

}
