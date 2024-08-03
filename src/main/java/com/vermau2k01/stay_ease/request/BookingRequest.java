package com.vermau2k01.stay_ease.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingRequest {
    @NotEmpty(message = "Please provide the booking date")
    @NotBlank(message = "Please provide the booking date")
    private LocalDate from;
    @NotEmpty(message = "Please provide the checkout date")
    @NotBlank(message = "Please provide the checkout date")
    private LocalDate to;
}
