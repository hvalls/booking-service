package com.acme.bookingservice.adapter.db;

import com.acme.bookingservice.domain.query.DAO;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class H2DAO implements DAO {

    private final EntityManager entityManager;

    @Autowired
    public H2DAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public int getBookingCount() {
        var row = entityManager.find(JPABookingCount.class, JPABookingCount.ROW_PK);
        return row != null ? row.getCount() : 0;
    }

    @Override
    @Transactional
    public void incrementBookingCount() {
        var currentCount = entityManager.find(JPABookingCount.class, JPABookingCount.ROW_PK);
        if (currentCount != null) {
            currentCount.setCount(currentCount.getCount() + 1);
            return;
        }
        entityManager.persist(new JPABookingCount(1));
    }

}
