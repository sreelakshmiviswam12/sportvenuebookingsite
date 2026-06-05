package com.sportvenue.sportsvenuebookingsite.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

@Entity
@Table(name = "venue_regulations")
@Data
public class VenueRegulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private String rules;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
}