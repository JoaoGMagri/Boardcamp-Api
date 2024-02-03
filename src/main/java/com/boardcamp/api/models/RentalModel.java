package com.boardcamp.api.models;

import com.boardcamp.api.dtos.RentalDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class RentalModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String rentDate;

    @Column(nullable = false)
    private int dayRented;

    @Column(nullable = true)
    private String returnDate;

    @Column(nullable = false)
    private int originalPrice;

    @Column(nullable = false)
    private int delayFee;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerModel customer;
    
    @ManyToOne
    @JoinColumn(name = "gameId")
    private GameModel game;

    public RentalModel(RentalDTO dto, String date, int priceTotal, CustomerModel customer, GameModel game) {
        this.rentDate = date;
        this.dayRented = dto.getDaysRented();
        this.returnDate = null;
        this.originalPrice = priceTotal;
        this.delayFee = 0;
        this.customer = customer;
        this.game = game;
    }

    public RentalModel(RentalModel rental, String dateReturn, int priceDelay, GameModel game) {
        this.id = rental.getId();
        this.rentDate = rental.getRentDate();
        this.dayRented = rental.getDayRented();
        this.returnDate = dateReturn;
        this.originalPrice = rental.getOriginalPrice();
        this.delayFee = priceDelay;
        this.customer = rental.getCustomer();
        this.game = game;
    }

}
