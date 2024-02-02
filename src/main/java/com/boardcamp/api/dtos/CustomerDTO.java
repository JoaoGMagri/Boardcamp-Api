package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {
    
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "CPF is required")
    @Size(min = 11, max = 11, message = "The CPF must have 11 numbers!")
    private String cpf;

}
