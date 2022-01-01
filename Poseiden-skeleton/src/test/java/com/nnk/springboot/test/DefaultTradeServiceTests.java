package com.nnk.springboot.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.impl.DefaultTradeService;

@ExtendWith(MockitoExtension.class)
class DefaultTradeServiceTests {

	@Mock
	private TradeRepository mockRepository;

	@InjectMocks
	private DefaultTradeService tradeService;

	private Trade trade;

	@BeforeEach
	void setUp() {
		trade = new Trade("Trade Account", "Type", 1d);
	}

	@Test
	void createTrade_whenCalled_returnCreatedTrade() {
		when(mockRepository.save(trade)).thenReturn(trade);

		Trade test = tradeService.createTrade(trade);

		assertThat(test).isEqualTo(trade);
	}

	@Test
	void createTrade_whenCalled_useRepository() {
		tradeService.createTrade(trade);

		verify(mockRepository, times(1)).save(trade);
	}

	@Test
	void findAllTrades_whenNoTradesAreFound_returnEmptyList() {
		when(mockRepository.findAll()).thenReturn(new ArrayList<Trade>());

		List<Trade> test = tradeService.findAllTrades();

		assertThat(test).isEmpty();
	}

	@Test
	void findAllTrades_whenTradesAreFound_returnListOfTrade() {
		when(mockRepository.findAll()).thenReturn(List.of(trade));

		List<Trade> test = tradeService.findAllTrades();

		assertThat(test.size()).isEqualTo(1);
	}

	@Test
	void findAllTrades_whenCalled_useRepository() {
		tradeService.findAllTrades();

		verify(mockRepository, times(1)).findAll();
	}

	@Test
	void findById_whenTradeIsNotFound_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> tradeService.findTradeById(1));
	}

	@Test
	void findById_whenTradeIsFound_returnTrade() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(trade));

		Trade test = tradeService.findTradeById(1);

		assertThat(test).isEqualTo(trade);
	}

	@Test
	void findById_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(trade));

		tradeService.findTradeById(1);

		verify(mockRepository, times(1)).findById(1);
	}

	@Test
	void updateTrade_whenCalled_returnUpdatedTrade() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(trade));
		when(mockRepository.save(trade)).thenReturn(trade);

		Trade test = tradeService.updateTrade(1, trade);

		assertThat(test).isEqualTo(trade);
	}

	@Test
	void updateTrade_whenTradeDoesntExist_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> tradeService.updateTrade(1, trade));
	}

	@Test
	void updateTrade_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(trade));

		tradeService.updateTrade(1, trade);

		verify(mockRepository, times(1)).findById(1);
		verify(mockRepository, times(1)).save(trade);
	}

	@Test
	void deleteTradeById_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(trade));

		tradeService.deleteTradeById(1);

		verify(mockRepository, times(1)).deleteById(1);
	}

	@Test
	void deleteTradeById_whenTradeNotFound_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> tradeService.deleteTradeById(1));
	}
}
