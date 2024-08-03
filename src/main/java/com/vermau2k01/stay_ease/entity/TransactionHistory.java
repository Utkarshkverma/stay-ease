package com.vermau2k01.stay_ease.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private int roomId;
    private LocalDate bookingFrom;
    private LocalDate bookingTo;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int price;
}
