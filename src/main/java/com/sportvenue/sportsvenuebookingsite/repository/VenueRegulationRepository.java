package com.sportvenue.sportsvenuebookingsite.repository;

import com.sportvenue.sportsvenuebookingsite.entity.VenueRegulation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VenueRegulationRepository extends JpaRepository<VenueRegulation, Long> {
    List<VenueRegulation> findByVenueId(Long venueId);
}