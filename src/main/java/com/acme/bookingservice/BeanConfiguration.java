package com.acme.bookingservice;

import com.acme.bookingservice.adapter.db.H2BookingRepository;
import com.acme.bookingservice.adapter.db.H2DAO;
import com.acme.bookingservice.adapter.db.JPABookingRepository;
import com.acme.bookingservice.adapter.eventbus.InMemoryEventPublisherSubscriber;
import com.acme.bookingservice.domain.command.BookingRepository;
import com.acme.bookingservice.domain.query.DAO;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    private final InMemoryEventPublisherSubscriber eventBus = new InMemoryEventPublisherSubscriber();

    @Bean
    public BookingRepository bookingRepository(JPABookingRepository repo) {
        return new H2BookingRepository(repo);
    }

    @Bean
    public InMemoryEventPublisherSubscriber eventPublisherSubscriber() {
        return eventBus;
    }

    @Bean
    public DAO dao(EntityManager entityManager) {
        return new H2DAO(entityManager);
    }

}
