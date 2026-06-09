package com.sportvenue.sportsvenuebookingsite.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "venue_images")
@Data
public class VenueImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private String imageName;

    private String imageType;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
}