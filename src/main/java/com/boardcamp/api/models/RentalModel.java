package com.boardcamp.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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

    @ManyToMany
    @JoinColumn(name = "customersId", referencedColumnName = "id", nullable = false)
    private Long customer;
    
    @ManyToMany
    @JoinColumn(name = "gamesId", referencedColumnName = "id", nullable = false)
    private Long game;

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


}
