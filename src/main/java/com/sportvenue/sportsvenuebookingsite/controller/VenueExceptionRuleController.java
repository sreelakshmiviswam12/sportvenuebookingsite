package com.sportvenue.sportsvenuebookingsite.controller;

import com.sportvenue.sportsvenuebookingsite.entity.VenueExceptionRule;
import com.sportvenue.sportsvenuebookingsite.service.VenueExceptionRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/exceptions")
@RequiredArgsConstructor
public class VenueExceptionRuleController {

    private final VenueExceptionRuleService exceptionRuleService;

    @PostMapping
    public ResponseEntity<VenueExceptionRule> create(@RequestBody VenueExceptionRule rule) {
        return ResponseEntity.ok(exceptionRuleService.saveExceptionRule(rule));
    }

    @GetMapping("/venue/{venueId}")
    public ResponseEntity<List<VenueExceptionRule>> getByVenue(@PathVariable Long venueId) {
        return ResponseEntity.ok(exceptionRuleService.getByVenue(venueId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exceptionRuleService.deleteExceptionRule(id);
        return ResponseEntity.noContent().build();
    }
}
