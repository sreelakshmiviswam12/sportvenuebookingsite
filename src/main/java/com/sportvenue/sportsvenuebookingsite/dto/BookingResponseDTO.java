package com.sportvenue.sportsvenuebookingsite.dto;

import com.sportvenue.sportsvenuebookingsite.entity.BookingStatus;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingResponseDTO {
    private Long id;
    private String customerName;
    private Long customerId;
    private String venueName;
    private Long venueId;
    private String venueLocation;
    private Double venuePrice;
    private Integer duration;
    private LocalDate date;
    private BookingStatus status;
}