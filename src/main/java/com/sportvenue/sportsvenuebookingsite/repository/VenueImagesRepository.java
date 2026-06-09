package com.sportvenue.sportsvenuebookingsite.repository;

import com.sportvenue.sportsvenuebookingsite.entity.VenueImages;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VenueImagesRepository extends JpaRepository<VenueImages, Long> {
    List<VenueImages> findByVenueId(Long venueId);
    Long countByVenueId(Long venueId);
}