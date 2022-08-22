package org.oleksandr.tours.repo;

import org.oleksandr.tours.model.Reservation;
import org.oleksandr.tours.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

//    @Query("Select r from reservations r where r.user_id = ?1")
    List<Reservation> findByUser(User user);

//    @Query("Select t from TourSchedule t where t.id = ?1")
//    TourSchedule findById(String id);

//    @Query("Select t from TourSchedule t where t.attraction = ?1")
//    List<TourSchedule> findBy(Long attractionId);
}