package com.sportvenue.sportsvenuebookingsite.controller;

import com.sportvenue.sportsvenuebookingsite.entity.VenueRegulation;
import com.sportvenue.sportsvenuebookingsite.service.VenueRegulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/regulations")
@RequiredArgsConstructor
public class VenueRegulationController {

    private final VenueRegulationService regulationService;

    @PostMapping
    public ResponseEntity<VenueRegulation> create(@RequestBody VenueRegulation regulation) {
        return ResponseEntity.ok(regulationService.saveRegulation(regulation));
    }

    @GetMapping("/venue/{venueId}")
    public ResponseEntity<List<VenueRegulation>> getByVenue(@PathVariable Long venueId) {
        return ResponseEntity.ok(regulationService.getByVenue(venueId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        regulationService.deleteRegulation(id);
        return ResponseEntity.noContent().build();
    }
}