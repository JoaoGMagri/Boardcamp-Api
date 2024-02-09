package com.boardcamp.api.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.exceptions.ExceptionNotFound;
import com.boardcamp.api.exceptions.ExceptionUnprocessableEntity;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.repositories.RentalRepository;

@Service
public class RentalService {

    LocalDate data = LocalDate.now();

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

        CustomerModel customer = customerRepository.findById(dto.getCustomerId()).orElseThrow( () -> new ExceptionNotFound("User not found!"));
        GameModel     game     = gameRepository.findById(dto.getGameId()).orElseThrow( () -> new ExceptionNotFound("Game not found!"));
        
        if ( game.getStockTotal() < 1 ){
            throw new ExceptionUnprocessableEntity("No copies available!");
        }

        GameModel newGame = new GameModel(game, game.getStockTotal()-1);
        gameRepository.save(newGame);

        int priceTotal = game.getPricePerDay() * dto.getDaysRented();

        return rentalRepository.save(new RentalModel(dto, data.toString(), priceTotal, customer, game) );

    }

    public RentalModel update(Long id) {

        int updatePrice = 000;

        RentalModel rental = rentalRepository.findById(id).orElseThrow( () -> new ExceptionNotFound("Rental not found!"));
        
        if(rental.getReturnDate() != null) {
            throw new ExceptionUnprocessableEntity("Return has already been made!");
        }

        GameModel newGame = new GameModel(rental.getGame(), rental.getGame().getStockTotal()+1);
        gameRepository.save(newGame);
        

        LocalDate rentDate = LocalDate.parse(rental.getRentDate());
        Integer daysBetween = Math.toIntExact(ChronoUnit.DAYS.between(rentDate, data));
        System.out.println(daysBetween);
        if (daysBetween > rental.getDayRented()) {
            int daysDiff = daysBetween - rental.getDayRented();
            updatePrice = rental.getGame().getPricePerDay() * daysDiff;
        }

        return rentalRepository.save(new RentalModel(rental, data.toString(), updatePrice, newGame));

    }

}
