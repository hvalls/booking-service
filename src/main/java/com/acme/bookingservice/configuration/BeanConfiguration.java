package com.acme.bookingservice.configuration;

import com.acme.bookingservice.adapter.eventbus.EventBus;
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
        // TODO: Use in memory event bus
        return () -> {};
    }

}
