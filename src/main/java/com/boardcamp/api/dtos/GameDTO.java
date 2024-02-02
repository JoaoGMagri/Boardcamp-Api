package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GameDTO {
    
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Image is required")
    private String image;

    @NotNull(message = "Quantity in stock is required")
    @Positive(message = "You must have at least one in stock")
    private int stockTotal;

    @NotNull(message = "Price per day is required")
    @Positive(message = "The price cannot be zero")
    private int pricePerDay;

}
