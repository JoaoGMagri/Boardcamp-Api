package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GameDTO {
    
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Image is required")
    private String image;

    @NotNull(message = "Quantity in stock is required")
    private int stockTotal;

    @NotNull(message = "Price per day is required")
    private int pricePerDay;

}
