package com.sportvenue.sportsvenuebookingsite.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "venue_exception_rules")
@Data
public class VenueExceptionRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalTime unavailableStart;

    private LocalTime unavailableEnd;

    private LocalDate exceptionDate;

    @Enumerated(EnumType.STRING)
    private VenueExceptionType venueExceptionType;

    private String reason;

    @Enumerated(EnumType.STRING)
    private ExceptionStatus exceptionStatus;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
}