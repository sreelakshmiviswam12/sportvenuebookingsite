package com.sportvenue.sportsvenuebookingsite.service;

import com.sportvenue.sportsvenuebookingsite.dto.BookingResponseDTO;
import com.sportvenue.sportsvenuebookingsite.entity.Booking;
import com.sportvenue.sportsvenuebookingsite.entity.BookingStatus;
import com.sportvenue.sportsvenuebookingsite.repository.BookingRepository;
import com.sportvenue.sportsvenuebookingsite.repository.UserRepository;
import com.sportvenue.sportsvenuebookingsite.repository.VenueRepository;
import com.sportvenue.sportsvenuebookingsite.entity.User;
import com.sportvenue.sportsvenuebookingsite.entity.Venue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final VenueRepository venueRepository;

    public BookingResponseDTO saveBooking(Booking booking) {
        booking.setStatus(BookingStatus.PENDING);
        Booking saved = bookingRepository.save(booking);

        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(saved.getId());
        dto.setDuration(saved.getDuration());
        dto.setDate(saved.getDate());
        dto.setStatus(saved.getStatus());

        // Fetch customer separately
        if (saved.getCustomer() != null) {
            userRepository.findById(saved.getCustomer().getId()).ifPresent(user -> {
                dto.setCustomerId(user.getId());
                dto.setCustomerName(user.getName());
            });
        }

        // Fetch venue separately
        if (saved.getVenue() != null) {
            venueRepository.findById(saved.getVenue().getId()).ifPresent(venue -> {
                dto.setVenueId(venue.getId());
                dto.setVenueName(venue.getName());
                dto.setVenueLocation(venue.getLocation());
                dto.setVenuePrice(venue.getPrice());
            });
        }

        return dto;
    }

    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<BookingResponseDTO> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<BookingResponseDTO> getBookingsByVenue(Long venueId) {
        return bookingRepository.findByVenueId(venueId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public BookingResponseDTO updateStatus(Long id, BookingStatus status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(status);
        Booking saved = bookingRepository.save(booking);
        return toDTO(saved);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    private BookingResponseDTO toDTO(Booking b) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(b.getId());
        dto.setDuration(b.getDuration());
        dto.setDate(b.getDate());
        dto.setStatus(b.getStatus());
        if (b.getCustomer() != null) {
            dto.setCustomerId(b.getCustomer().getId());
            dto.setCustomerName(b.getCustomer().getName());
        }
        if (b.getVenue() != null) {
            dto.setVenueId(b.getVenue().getId());
            dto.setVenueName(b.getVenue().getName());
            dto.setVenueLocation(b.getVenue().getLocation());
            dto.setVenuePrice(b.getVenue().getPrice());
        }
        return dto;
    }
}