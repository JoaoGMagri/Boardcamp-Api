package com.boardcamp.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.service.RentalService;

import jakarta.validation.Valid;

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

    @PostMapping
    public ResponseEntity<Object> postRental(@RequestBody @Valid RentalDTO body) {

        RentalModel model = rentalService.save(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(model);

    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Object> putRental(@PathVariable Long id) {

        RentalModel model = rentalService.update(id);

        return ResponseEntity.status(HttpStatus.OK).body(model);

    }


}
