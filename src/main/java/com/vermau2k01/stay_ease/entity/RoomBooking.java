package com.vermau2k01.stay_ease.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Users user;
    private LocalDate bookingFrom;
    private LocalDate bookingTo;
    @OneToOne
    private Rooms room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    @Transient
    private double price()
    {
        return ChronoUnit.DAYS.between(checkOutDate, checkInDate)* room.getPrice();
    }

}
