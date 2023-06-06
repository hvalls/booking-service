package com.acme.bookingservice.configuration;

import com.acme.bookingservice.adapter.eventbus.InMemoryEventBus;
import com.acme.bookingservice.domain.common.event.EventBus;
import com.acme.bookingservice.domain.command.BookingRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public BookingRepository bookingRepository() {
        // TODO: use in memory database repository
        return booking -> {};
    }

    @Bean
    public EventBus eventBus() {
        return new InMemoryEventBus();
    }

}
