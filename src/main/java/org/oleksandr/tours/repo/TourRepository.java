package org.oleksandr.tours.repo;

import org.oleksandr.tours.model.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<TourSchedule, Long> {}