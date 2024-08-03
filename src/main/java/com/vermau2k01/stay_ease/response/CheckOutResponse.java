package com.vermau2k01.stay_ease.response;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutResponse {

    @NotEmpty(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email is not well formatted")
    private String emailId;
    @NotNull(message = "Booking from date cannot be null")
    @PastOrPresent(message = "Booking from date cannot be in the future")
    private LocalDate bookFrom;
    @NotNull(message = "Booking to date cannot be null")
    @FutureOrPresent(message = "Booking to date must be today or in the future")
    private LocalDate bookTo;
    @NotNull(message = "Booking from date cannot be null")
    @PastOrPresent(message = "Booking from date cannot be in the future")
    private LocalDate checkInDate;
    @NotNull(message = "Booking to date cannot be null")
    @FutureOrPresent(message = "Booking to date must be today or in the future")
    private LocalDate checkOutDate;
    @Min(value = 1000,message = "Price cannot be less then 1000")
    private int price;

}
