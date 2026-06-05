package com.sportvenue.sportsvenuebookingsite.service;

import com.sportvenue.sportsvenuebookingsite.entity.VenueRegulation;
import com.sportvenue.sportsvenuebookingsite.repository.VenueRegulationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueRegulationService {

    private final VenueRegulationRepository regulationRepository;

    public VenueRegulation saveRegulation(VenueRegulation regulation) {
        return regulationRepository.save(regulation);
    }

    public List<VenueRegulation> getByVenue(Long venueId) {
        return regulationRepository.findByVenueId(venueId);
    }

    public void deleteRegulation(Long id) {
        regulationRepository.deleteById(id);
    }
}