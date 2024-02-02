package com.boardcamp.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.exceptions.ExistingCPFException;
import com.boardcamp.api.exceptions.UserNotFoundException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

@Service
public class CustomerService {
    
    final CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerModel> findAll() {
        return customerRepository.findAll();
    }

    public CustomerModel findById(Long id) {
        return customerRepository.findById(id).orElseThrow( () -> new UserNotFoundException("This user does not exist!"));
    }

    public CustomerModel save(CustomerDTO dto) {

        if ( customerRepository.existsByCpf(dto.getCpf()) ) {
            throw new ExistingCPFException("This CPF is already registered!");
        }

        return customerRepository.save( new CustomerModel(dto) );
    }

}
