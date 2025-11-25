package com.blablacar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlaBlaCar Application {

    public static void main(String[] args) {
        SpringApplication.run(BlaBlaCar Application.class, args);

        System.out.println("\n" + "=".repeat(70));
        System.out.println("🚗 BLABLACAR-STYLE RIDESHARING PLATFORM STARTED!");
        System.out.println("=".repeat(70));
        System.out.println("✅ Application: http://localhost:8080");
        System.out.println("✅ H2 Console: http://localhost:8080/h2-console");
        System.out.println("=".repeat(70));
        System.out.println("📱 Features:");
        System.out.println("   - Real-time ride search");
        System.out.println("   - Instant & Request bookings");
        System.out.println("   - Driver & Passenger profiles");
        System.out.println("   - Ride preferences & filters");
        System.out.println("   - Modern BlaBlaCar-style UI");
        System.out.println("=".repeat(70) + "\n");
    }
}
