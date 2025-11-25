package com.blablacar.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String phone;

    private String profilePhoto;

    @Column(length = 500)
    private String bio;

    private String city;
    private String country;

    private LocalDateTime dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.MEMBER;

    // Driver specific
    private String vehicleBrand;
    private String vehicleModel;
    private String vehicleColor;
    private String licensePlate;
    private Integer vehicleSeats;

    // Verification
    private Boolean isEmailVerified = false;
    private Boolean isPhoneVerified = false;
    private Boolean isIdVerified = false;

    // OTP
    private String otp;
    private LocalDateTime otpExpiresAt;

    // Ratings
    private Double rating = 5.0;
    private Integer totalRatings = 0;

    // Status
    private Boolean isActive = true;
    private Boolean isBlocked = false;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum UserRole {
        MEMBER, DRIVER, ADMIN
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
