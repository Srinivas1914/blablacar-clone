package com.blablacar.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "ride_id")
    private Ride ride;

    @Column(nullable = false, length = 2000)
    private String content;

    private Boolean isRead = false;
    private LocalDateTime readAt;

    private LocalDateTime sentAt = LocalDateTime.now();
}
