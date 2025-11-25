package com.blablacar.service;

import com.blablacar.entity.Ride;
import com.blablacar.entity.User;
import com.blablacar.repository.RideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RideService {

    private final RideRepository rideRepository;

    @Transactional
    public Ride createRide(Ride ride) {
        ride.setAvailableSeats(ride.getTotalSeats());
        ride.setStatus(Ride.RideStatus.ACTIVE);
        ride.setViews(0);
        ride.setBookings(0);
        ride.setCreatedAt(LocalDateTime.now());
        return rideRepository.save(ride);
    }

    public List<Ride> searchRides(String departure, String destination, 
                                 LocalDate date, Integer seats) {
        if (date == null) {
            date = LocalDate.now();
        }
        if (seats == null) {
            seats = 1;
        }
        return rideRepository.searchRides(departure, destination, date, seats);
    }

    public List<Ride> getRecentRides() {
        return rideRepository.findTop10ByStatusOrderByCreatedAtDesc(Ride.RideStatus.ACTIVE);
    }

    public List<Ride> getDriverRides(User driver) {
        return rideRepository.findByDriver(driver);
    }

    public Ride getRideById(Long id) {
        return rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
    }

    @Transactional
    public void incrementViews(Long rideId) {
        Ride ride = getRideById(rideId);
        ride.setViews(ride.getViews() + 1);
        rideRepository.save(ride);
    }

    @Transactional
    public void updateRideStatus(Long rideId, Ride.RideStatus status) {
        Ride ride = getRideById(rideId);
        ride.setStatus(status);
        ride.setUpdatedAt(LocalDateTime.now());
        rideRepository.save(ride);
    }

    @Transactional
    public void cancelRide(Long rideId) {
        updateRideStatus(rideId, Ride.RideStatus.CANCELLED);
    }
}
