package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalDTO {
    
    @NotNull(message = "Id user per day is required")
    @Positive(message = "The Id user cannot be zero")
    private Long customerId;
    
    @NotNull(message = "Id game per day is required")
    @Positive(message = "The Id game cannot be zero")
    private Long gameId;
    
    @NotNull(message = "Days rented per day is required")
    @Positive(message = "The Days rented cannot be zero")
    private int daysRented;

}
