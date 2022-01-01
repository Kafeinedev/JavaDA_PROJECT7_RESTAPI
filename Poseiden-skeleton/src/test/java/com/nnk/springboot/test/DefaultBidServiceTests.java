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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.impl.DefaultBidService;

@ExtendWith(MockitoExtension.class)
class DefaultBidServiceTests {

	@Mock
	private BidListRepository mockRepository;

	@InjectMocks
	private DefaultBidService bidService;

	private BidList bid;

	@BeforeEach
	void setUp() {
		bid = new BidList("this is a test", "aaaaa", 2.0d);
	}

	@Test
	void createBid_whenCalled_returnCreatedBid() {
		when(mockRepository.save(bid)).thenReturn(bid);

		BidList test = bidService.createBid(bid);

		assertThat(test).isEqualTo(bid);
	}

	@Test
	void createBid_whenCalled_useRepository() {
		bidService.createBid(bid);

		verify(mockRepository, times(1)).save(bid);
	}

	@Test
	void findAllBids_whenNoBidsAreFound_returnEmptyList() {
		when(mockRepository.findAll()).thenReturn(new ArrayList<BidList>());

		List<BidList> test = bidService.findAllBids();

		assertThat(test).isEmpty();
	}

	@Test
	void findAllBids_whenBidsAreFound_returnListOfBid() {
		List<BidList> arr = new ArrayList<BidList>();
		arr.add(bid);
		when(mockRepository.findAll()).thenReturn(arr);

		List<BidList> test = bidService.findAllBids();

		assertThat(test.size()).isEqualTo(1);
	}

	@Test
	void findAllBids_whenCalled_useRepository() {
		bidService.findAllBids();

		verify(mockRepository, times(1)).findAll();
	}

	@Test
	void findById_whenBidIsNotFound_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> bidService.findBidById(1));
	}

	@Test
	void findById_whenBidIsFound_returnBid() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(bid));

		BidList test = bidService.findBidById(1);

		assertThat(test).isEqualTo(bid);
	}

	@Test
	void findById_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(bid));

		bidService.findBidById(1);

		verify(mockRepository, times(1)).findById(1);
	}

	@Test
	void updateBid_whenCalled_returnUpdatedBid() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(bid));
		when(mockRepository.save(bid)).thenReturn(bid);

		BidList test = bidService.updateBid(1, bid);

		assertThat(test).isEqualTo(bid);
	}

	@Test
	void updateBid_whenBidDoesntExist_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> bidService.updateBid(1, bid));
	}

	@Test
	void updateBid_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(bid));

		bidService.updateBid(1, bid);

		verify(mockRepository, times(1)).findById(1);
		verify(mockRepository, times(1)).save(bid);
	}

	@Test
	void deleteBidById_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(bid));

		bidService.deleteBidById(1);

		verify(mockRepository, times(1)).deleteById(1);
	}

	@Test
	void deleteBidById_whenBidNotFound_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> bidService.deleteBidById(1));
	}
}
