package com.sportvenue.sportsvenuebookingsite.controller;

import com.sportvenue.sportsvenuebookingsite.dto.BookingResponseDTO;
import com.sportvenue.sportsvenuebookingsite.entity.Booking;
import com.sportvenue.sportsvenuebookingsite.entity.BookingStatus;
import com.sportvenue.sportsvenuebookingsite.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.saveBooking(booking));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BookingResponseDTO>> getByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(bookingService.getBookingsByCustomer(customerId));
    }

    @GetMapping("/venue/{venueId}")
    public ResponseEntity<List<BookingResponseDTO>> getByVenue(@PathVariable Long venueId) {
        return ResponseEntity.ok(bookingService.getBookingsByVenue(venueId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BookingResponseDTO> updateStatus(@PathVariable Long id,
                                                           @RequestParam BookingStatus status) {
        return ResponseEntity.ok(bookingService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}