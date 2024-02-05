package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.exceptions.ExceptionConflict;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.service.CustomerService;

@SpringBootTest
public class CustomerUnitTest {
    
    @InjectMocks
    CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;


    @Test
    void givenRepeatedCPFUser_whenCreatingRecipe_thenThrowsError(){
        CustomerDTO customerDto = new CustomerDTO("name", "12345678910");
        doReturn(true).when(customerRepository).existsByCpf(any());

        ExceptionConflict exception = assertThrows(ExceptionConflict.class, () -> customerService.save(customerDto));

        assertNotNull(exception);
		assertEquals("This CPF is already registered!", exception.getMessage());
		verify(customerRepository, times(0)).save(any());
		verify(customerRepository, times(1)).existsByCpf(any());
    }

}
