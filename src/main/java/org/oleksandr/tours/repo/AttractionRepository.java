package org.oleksandr.tours.repo;

import org.oleksandr.tours.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    @Query("Select a from Attraction a where a.id = ?1")
    Attraction findById(String id);
}