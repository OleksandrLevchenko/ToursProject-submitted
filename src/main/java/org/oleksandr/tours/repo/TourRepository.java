package org.oleksandr.tours.repo;

import org.oleksandr.tours.model.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<TourSchedule, Long> {
//    @Query("Select t from TourSchedule t where t.id = ?1")
//    TourSchedule findById(String id);

//    @Query("Select t from TourSchedule t where t.attraction = ?1")
//    List<TourSchedule> findBy(Long attractionId);
}