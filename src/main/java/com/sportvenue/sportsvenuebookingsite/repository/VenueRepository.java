package com.sportvenue.sportsvenuebookingsite.repository;

import com.sportvenue.sportsvenuebookingsite.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    List<Venue> findByActiveStatusTrue();
    List<Venue> findByOwnerId(Long ownerId);
}