package com.vermau2k01.stay_ease.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CheckInResponse {

    @Email(message = "Email is not well formatted")
    private String email;
    private int roomNo;
    @NotNull(message = "Booking from date cannot be null")
    @PastOrPresent(message = "Booking from date cannot be in the future")
    private LocalDate bookingFromDate;
    @NotNull(message = "Booking to date cannot be null")
    @FutureOrPresent(message = "Booking to date must be today or in the future")
    private LocalDate bookingToDate;
    @NotNull(message = "Check-in date cannot be null")
    @FutureOrPresent(message = "Check-in date must be today or in the future")
    private LocalDate checkInDate;

}
