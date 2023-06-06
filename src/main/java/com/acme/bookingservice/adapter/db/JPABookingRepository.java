package com.acme.bookingservice.adapter.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPABookingRepository extends CrudRepository<JPABooking, String> {
}
