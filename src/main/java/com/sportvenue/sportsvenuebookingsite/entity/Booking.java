package com.sportvenue.sportsvenuebookingsite.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bookings")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "venue_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Venue venue;

    private Integer duration;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}