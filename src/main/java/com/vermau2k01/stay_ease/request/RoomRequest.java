package com.vermau2k01.stay_ease.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequest {

    @NotEmpty(message = "Price cannot be empty")
    @NotBlank(message = "prices cannot be blank")
    @Min(value = 1000, message = "Minimum price must be 1000 Rs.")
    private double price;
}
