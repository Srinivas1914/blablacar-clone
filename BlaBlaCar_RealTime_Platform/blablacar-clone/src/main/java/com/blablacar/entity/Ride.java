package com.blablacar.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
@Data
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    // Route details
    @Column(nullable = false)
    private String departureCity;

    @Column(nullable = false)
    private String destinationCity;

    private String departureAddress;
    private String destinationAddress;

    @Column(nullable = false)
    private LocalDate departureDate;

    @Column(nullable = false)
    private LocalTime departureTime;

    private Integer durationMinutes;
    private Integer distanceKm;

    // Booking details
    @Column(nullable = false)
    private Integer totalSeats;

    @Column(nullable = false)
    private Integer availableSeats;

    @Column(nullable = false)
    private Double pricePerSeat;

    // Preferences
    private Boolean smokingAllowed = false;
    private Boolean petsAllowed = false;
    private Boolean musicAllowed = true;

    @Enumerated(EnumType.STRING)
    private ChattinessLevel chattiness = ChattinessLevel.MODERATE;

    // Vehicle details
    private String vehicleInfo;
    private String vehicleComfort;

    // Additional info
    @Column(length = 1000)
    private String notes;

    private Boolean instantBooking = true;
    private Boolean womenOnly = false;

    @Enumerated(EnumType.STRING)
    private RideStatus status = RideStatus.ACTIVE;

    private Integer views = 0;
    private Integer bookings = 0;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum ChattinessLevel {
        QUIET, MODERATE, CHATTY
    }

    public enum RideStatus {
        ACTIVE, COMPLETED, CANCELLED, FULL
    }
}
