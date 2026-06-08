package com.sportvenue.sportsvenuebookingsite.service;

import com.sportvenue.sportsvenuebookingsite.dto.BookingResponseDTO;
import com.sportvenue.sportsvenuebookingsite.entity.Booking;
import com.sportvenue.sportsvenuebookingsite.entity.BookingStatus;
import com.sportvenue.sportsvenuebookingsite.entity.Venue;
import com.sportvenue.sportsvenuebookingsite.entity.VenueRegulation;
import com.sportvenue.sportsvenuebookingsite.entity.VenueExceptionRule;
import com.sportvenue.sportsvenuebookingsite.repository.BookingRepository;
import com.sportvenue.sportsvenuebookingsite.repository.UserRepository;
import com.sportvenue.sportsvenuebookingsite.repository.VenueRepository;
import com.sportvenue.sportsvenuebookingsite.repository.VenueRegulationRepository;
import com.sportvenue.sportsvenuebookingsite.repository.VenueExceptionRuleRepository;
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
    private final VenueRegulationRepository regulationRepository;
    private final VenueExceptionRuleRepository exceptionRuleRepository;

    public BookingResponseDTO saveBooking(Booking booking) {

        // BL1 - Check if venue exists
        Venue venue = venueRepository.findById(booking.getVenue().getId())
                .orElseThrow(() -> new RuntimeException("Venue not found!"));

        // BL2 - Check if venue is active
        if (!venue.getActiveStatus()) {
            throw new RuntimeException("Venue is not active! Cannot book.");
        }

        // BL3 - Check if venue is available for booking
        if (!venue.getBookingStatus()) {
            throw new RuntimeException("Venue is not available for booking!");
        }

        // BL4 - Check double booking
        if (bookingRepository.existsByVenueIdAndDate(
                booking.getVenue().getId(), booking.getDate())) {
            throw new RuntimeException("Venue is already booked on this date!");
        }

        // BL5 - Check Venue Regulation Hours
        if (booking.getStartTime() != null && booking.getEndTime() != null) {
            List<VenueRegulation> regulations = regulationRepository
                    .findByVenueId(booking.getVenue().getId());

            if (!regulations.isEmpty()) {
                boolean withinHours = regulations.stream().anyMatch(reg ->
                        (booking.getStartTime().equals(reg.getStartTime()) ||
                                booking.getStartTime().isAfter(reg.getStartTime())) &&
                                (booking.getEndTime().equals(reg.getEndTime()) ||
                                        booking.getEndTime().isBefore(reg.getEndTime()))
                );
                if (!withinHours) {
                    throw new RuntimeException(
                            "Booking time is outside venue operating hours! " +
                                    "Venue operates between " +
                                    regulations.get(0).getStartTime() +
                                    " and " +
                                    regulations.get(0).getEndTime());
                }
            }

            // BL6 - Check Venue Exception Rules
            List<VenueExceptionRule> exceptions = exceptionRuleRepository
                    .findByVenueId(booking.getVenue().getId());

            for (VenueExceptionRule exception : exceptions) {
                boolean overlaps =
                        !booking.getStartTime().isAfter(exception.getUnavailableEnd()) &&
                                !booking.getEndTime().isBefore(exception.getUnavailableStart());
                if (overlaps) {
                    throw new RuntimeException(
                            "Venue is unavailable during this time slot: " +
                                    exception.getName());
                }
            }
        }

        booking.setStatus(BookingStatus.PENDING);
        Booking saved = bookingRepository.save(booking);

        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(saved.getId());
        dto.setDuration(saved.getDuration());
        dto.setDate(saved.getDate());
        dto.setStartTime(saved.getStartTime());
        dto.setEndTime(saved.getEndTime());
        dto.setStatus(saved.getStatus());

        if (saved.getCustomer() != null) {
            userRepository.findById(saved.getCustomer().getId()).ifPresent(user -> {
                dto.setCustomerId(user.getId());
                dto.setCustomerName(user.getName());
            });
        }

        if (saved.getVenue() != null) {
            venueRepository.findById(saved.getVenue().getId()).ifPresent(v -> {
                dto.setVenueId(v.getId());
                dto.setVenueName(v.getName());
                dto.setVenueLocation(v.getLocation());
                dto.setVenuePrice(v.getPrice());
                dto.setTotalPrice(v.getPrice() * saved.getDuration());
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
        dto.setStartTime(b.getStartTime());
        dto.setEndTime(b.getEndTime());
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
            dto.setTotalPrice(b.getVenue().getPrice() * b.getDuration());
        }
        return dto;
    }
}