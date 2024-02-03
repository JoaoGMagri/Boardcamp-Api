package com.boardcamp.api.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.exceptions.ExistingGameNameException;
import com.boardcamp.api.exceptions.UserNotFoundException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.repositories.RentalRepository;

@Service
public class RentalService {

    Date dataHoraAtual = new Date();
    String data = new SimpleDateFormat("dd-MM-yyyy").format(dataHoraAtual);

    final RentalRepository rentalRepository;
    final GameRepository gameRepository;
    final CustomerRepository customerRepository;

    RentalService(RentalRepository rentalRepository, GameRepository gameRepository, CustomerRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.gameRepository = gameRepository;
        this.customerRepository = customerRepository;
    }

    public List<RentalModel> findAll() {
        return rentalRepository.findAll();
    }

    public RentalModel save(RentalDTO dto) {

        CustomerModel customer = customerRepository.findById(dto.getCustomerId()).orElseThrow( () -> new UserNotFoundException("1"));
        GameModel     game     = gameRepository.findById(dto.getGameId()).orElseThrow( () -> new UserNotFoundException("2"));
        
        if ( game.getStockTotal() < 1 ){
            throw new ExistingGameNameException("3");
        }

        GameModel newGame = new GameModel(game, game.getStockTotal()-1);
        gameRepository.save(newGame);

        int priceTotal = game.getPricePerDay() * dto.getDaysRented();

        return rentalRepository.save(new RentalModel(dto, data, priceTotal, customer, game) );

    }


}
