package com.acme.bookingservice.util;

import com.acme.bookingservice.domain.query.DAO;

public class MockDao implements DAO {

    public boolean getBookingCountCalled = false;
    public boolean incrementBookingCountCalled = false;

    @Override
    public int getBookingCount() {
        getBookingCountCalled = true;
        return 0;
    }

    @Override
    public void incrementBookingCount() {
        incrementBookingCountCalled = true;
    }
}
