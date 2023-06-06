package com.acme.bookingservice.adapter.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class JPABookingCount {

    /**
     * Booking count is being stored in this table in one and only row.
     *
     */
    public static final String ROW_PK = "1";

    @Id
    private String id;
    private Integer count;

    public JPABookingCount() {
    }

    public JPABookingCount(Integer count) {
        this.id = ROW_PK;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
