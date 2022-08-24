package org.oleksandr.tours.repo;

import org.oleksandr.tours.model.Reservation;
import org.oleksandr.tours.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
}