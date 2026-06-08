package com.sportvenue.sportsvenuebookingsite.repository;

import com.sportvenue.sportsvenuebookingsite.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b JOIN FETCH b.customer JOIN FETCH b.venue WHERE b.id = :id")
    Optional<Booking> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT b FROM Booking b JOIN FETCH b.customer JOIN FETCH b.venue WHERE b.customer.id = :customerId")
    List<Booking> findByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT b FROM Booking b JOIN FETCH b.customer JOIN FETCH b.venue WHERE b.venue.id = :venueId")
    List<Booking> findByVenueId(@Param("venueId") Long venueId);

    // BL3 - Check double booking
    boolean existsByVenueIdAndDate(Long venueId, LocalDate date);
}