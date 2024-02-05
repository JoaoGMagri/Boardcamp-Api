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

import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.exceptions.ExceptionConflict;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.service.GameService;

@SpringBootTest
class GameUnitTest {
	
	@InjectMocks
	GameService gameService;

	@Mock
	private GameRepository gameRepository;

	@Test
	void givenRepeatedGame_whenCreatingRecipe_thenThrowsError(){

		GameDTO gameDto = new GameDTO("name", "image", 3, 1500);
		doReturn(true).when(gameRepository).existsByName(any());

		ExceptionConflict exception = assertThrows(ExceptionConflict.class, () -> gameService.save(gameDto));
		
		assertNotNull(exception);
		assertEquals("A game already exists!", exception.getMessage());
		verify(gameRepository, times(0)).save(any());
		verify(gameRepository, times(1)).existsByName(any());

	}

}
