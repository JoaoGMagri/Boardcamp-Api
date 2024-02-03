package com.boardcamp.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.service.RentalService;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    final RentalService rentalService;

    RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<RentalModel> getGames() {
        return rentalService.findAll();
    }


}