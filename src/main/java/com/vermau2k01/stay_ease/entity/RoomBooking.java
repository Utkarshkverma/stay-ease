package com.vermau2k01.stay_ease.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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

}
