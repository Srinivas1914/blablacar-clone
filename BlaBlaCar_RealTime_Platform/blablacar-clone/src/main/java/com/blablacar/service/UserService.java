package com.blablacar.service;

import com.blablacar.entity.User;
import com.blablacar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final OTPService otpService;

    @Transactional
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        if (userRepository.existsByPhone(user.getPhone())) {
            throw new RuntimeException("Phone already registered");
        }

        user.setIsEmailVerified(false);
        user.setCreatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        otpService.generateAndSaveOTP(savedUser);

        return savedUser;
    }

    @Transactional
    public boolean verifyEmail(Long userId, String otp) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (otpService.verifyOTP(user, otp)) {
            user.setIsEmailVerified(true);
            otpService.clearOTP(user);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Transactional
    public Map<String, Object> initiateLogin(String emailOrPhone, String password) {
        Map<String, Object> response = new HashMap<>();

        User user = userRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone)
                .orElse(null);

        if (user == null) {
            response.put("success", false);
            response.put("message", "User not found");
            return response;
        }

        if (!user.getPassword().equals(password)) {
            response.put("success", false);
            response.put("message", "Invalid password");
            return response;
        }

        if (!user.getIsEmailVerified()) {
            response.put("success", false);
            response.put("message", "Please verify your email");
            return response;
        }

        if (user.getIsBlocked()) {
            response.put("success", false);
            response.put("message", "Account blocked");
            return response;
        }

        otpService.generateAndSaveOTP(user);

        response.put("success", true);
        response.put("userId", user.getId());
        return response;
    }

    @Transactional
    public User verifyLogin(Long userId, String otp) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (otpService.verifyOTP(user, otp)) {
            otpService.clearOTP(user);
            return user;
        }

        throw new RuntimeException("Invalid OTP");
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public User updateProfile(Long userId, User updatedData) {
        User user = getUserById(userId);
        user.setFirstName(updatedData.getFirstName());
        user.setLastName(updatedData.getLastName());
        user.setBio(updatedData.getBio());
        user.setCity(updatedData.getCity());
        user.setCountry(updatedData.getCountry());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Transactional
    public void updateVehicleInfo(Long userId, String brand, String model, 
                                 String color, String plate, Integer seats) {
        User user = getUserById(userId);
        user.setVehicleBrand(brand);
        user.setVehicleModel(model);
        user.setVehicleColor(color);
        user.setLicensePlate(plate);
        user.setVehicleSeats(seats);
        user.setRole(User.UserRole.DRIVER);
        userRepository.save(user);
    }
}
