package com.sportvenue.sportsvenuebookingsite.controller;

import com.sportvenue.sportsvenuebookingsite.dto.VenueImagesDTO;
import com.sportvenue.sportsvenuebookingsite.entity.VenueImages;
import com.sportvenue.sportsvenuebookingsite.service.VenueImagesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/venue-images")
@RequiredArgsConstructor
public class VenueImagesController {

    private final VenueImagesService venueImagesService;

    @PostMapping
    public ResponseEntity<VenueImages> addImage(
            @Valid @RequestBody VenueImagesDTO dto) {
        return ResponseEntity.ok(venueImagesService.addImage(dto));
    }

    @GetMapping("/venue/{venueId}")
    public ResponseEntity<List<VenueImages>> getImagesByVenue(
            @PathVariable Long venueId) {
        return ResponseEntity.ok(venueImagesService.getImagesByVenue(venueId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        venueImagesService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}