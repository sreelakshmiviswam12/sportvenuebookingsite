package com.sportvenue.sportsvenuebookingsite.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.DayOfWeek;
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

    @Enumerated(EnumType.STRING)
    private DayOfWeek weekStartDay;

    @Enumerated(EnumType.STRING)
    private DayOfWeek weekEndDay;

    private Integer minDuration;

    private Integer bookBefore;

    private boolean isCurrentlyActive;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
}