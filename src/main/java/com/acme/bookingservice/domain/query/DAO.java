package com.acme.bookingservice.domain.query;

/**
 * This interface allows accessing data from the query side.
 * It is different from the repository because they work at different levels of
 * abstraction: repository works at aggregate level (DDD, domain objects) and
 * DAO operates at a lower level / raw data (there is no domain modeling in the query side, just projections)
 */
public interface DAO {

    int getBookingCount();

    void incrementBookingCount();

}
