package com.sportvenue.sportsvenuebookingsite.service;

import com.sportvenue.sportsvenuebookingsite.dto.VenueImagesDTO;
import com.sportvenue.sportsvenuebookingsite.entity.Venue;
import com.sportvenue.sportsvenuebookingsite.entity.VenueImages;
import com.sportvenue.sportsvenuebookingsite.repository.VenueImagesRepository;
import com.sportvenue.sportsvenuebookingsite.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueImagesService {

    private final VenueImagesRepository venueImagesRepository;
    private final VenueRepository venueRepository;

    public VenueImages addImage(VenueImagesDTO dto) {

        // Check venue exists
        Venue venue = venueRepository.findById(dto.getVenueId())
                .orElseThrow(() -> new RuntimeException("Venue not found!"));

        // Max 3 images check
        Long imageCount = venueImagesRepository.countByVenueId(dto.getVenueId());
        if (imageCount >= 3) {
            throw new RuntimeException(
                    "Maximum 3 images allowed per venue! " +
                            "Please delete an existing image first.");
        }

        VenueImages image = new VenueImages();
        image.setImageUrl(dto.getImageUrl());
        image.setImageName(dto.getImageName());
        image.setImageType(dto.getImageType());
        image.setVenue(venue);

        return venueImagesRepository.save(image);
    }

    public List<VenueImages> getImagesByVenue(Long venueId) {
        return venueImagesRepository.findByVenueId(venueId);
    }

    public void deleteImage(Long id) {
        venueImagesRepository.deleteById(id);
    }
}