package com.vermau2k01.stay_ease.request;

import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {

    @Min(value = 1000, message = "Minimum price must be 1000 Rs.")
    private int price;
}
