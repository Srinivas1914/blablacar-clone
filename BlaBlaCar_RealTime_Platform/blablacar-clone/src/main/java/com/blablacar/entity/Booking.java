package com.blablacar.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;

    @Column(nullable = false)
    private Integer seatsBooked;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(length = 500)
    private String message;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    private Boolean driverApproved = false;
    private LocalDateTime approvedAt;

    private Integer rating;
    private String review;
    private LocalDateTime reviewedAt;

    private LocalDateTime bookedAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum BookingStatus {
        PENDING, CONFIRMED, CANCELLED, COMPLETED
    }

    public enum PaymentStatus {
        PENDING, PAID, REFUNDED
    }
}
