package com.sportvenue.sportsvenuebookingsite.repository;

import com.sportvenue.sportsvenuebookingsite.entity.VenueExceptionRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VenueExceptionRuleRepository extends JpaRepository<VenueExceptionRule, Long> {
    List<VenueExceptionRule> findByVenueId(Long venueId);
}