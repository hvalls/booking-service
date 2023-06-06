package com.acme.bookingservice.adapter.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class JPABooking {

    @Id
    private String id;
    private String resourceId;

    public JPABooking() {
    }

    public JPABooking(String id, String resourceId) {
        this.id = id;
        this.resourceId = resourceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
