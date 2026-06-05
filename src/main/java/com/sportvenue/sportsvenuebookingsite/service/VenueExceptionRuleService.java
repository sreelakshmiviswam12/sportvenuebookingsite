package com.sportvenue.sportsvenuebookingsite.service;

import com.sportvenue.sportsvenuebookingsite.entity.VenueExceptionRule;
import com.sportvenue.sportsvenuebookingsite.repository.VenueExceptionRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueExceptionRuleService {

    private final VenueExceptionRuleRepository exceptionRuleRepository;

    public VenueExceptionRule saveExceptionRule(VenueExceptionRule rule) {
        return exceptionRuleRepository.save(rule);
    }

    public List<VenueExceptionRule> getByVenue(Long venueId) {
        return exceptionRuleRepository.findByVenueId(venueId);
    }

    public void deleteExceptionRule(Long id) {
        exceptionRuleRepository.deleteById(id);
    }
}
