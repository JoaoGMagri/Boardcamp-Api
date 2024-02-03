package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RentalDTO {
    
    @NotNull(message = "Id user per day is required")
    @Positive(message = "The Id user cannot be zero")
    private int customerId;
    
    @NotNull(message = "Id game per day is required")
    @Positive(message = "The Id game cannot be zero")
    private int gameId;
    
    @NotNull(message = "Days rented per day is required")
    @Positive(message = "The Days rented cannot be zero")
    private int daysRented;

}
