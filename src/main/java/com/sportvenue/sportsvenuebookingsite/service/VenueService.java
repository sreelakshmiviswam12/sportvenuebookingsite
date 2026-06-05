package com.sportvenue.sportsvenuebookingsite.service;

import com.sportvenue.sportsvenuebookingsite.entity.Venue;
import com.sportvenue.sportsvenuebookingsite.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    public Venue saveVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public List<Venue> getActiveVenues() {
        return venueRepository.findByActiveStatusTrue();
    }

    public Optional<Venue> getVenueById(Long id) {
        return venueRepository.findById(id);
    }

    public List<Venue> getVenuesByOwner(Long ownerId) {
        return venueRepository.findByOwnerId(ownerId);
    }

    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }
}