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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.impl.DefaultRatingService;

@ExtendWith(MockitoExtension.class)
class DefaultRatingServiceTests {

	@Mock
	private RatingRepository mockRepository;

	@InjectMocks
	private DefaultRatingService ratingService;

	private Rating rating;

	@BeforeEach
	void setUp() {
		rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
	}

	@Test
	void createRating_whenCalled_returnCreatedRating() {
		when(mockRepository.save(rating)).thenReturn(rating);

		Rating test = ratingService.createRating(rating);

		assertThat(test).isEqualTo(rating);
	}

	@Test
	void createRating_whenCalled_useRepository() {
		ratingService.createRating(rating);

		verify(mockRepository, times(1)).save(rating);
	}

	@Test
	void findAllRatings_whenNoRatingsAreFound_returnEmptyList() {
		when(mockRepository.findAll()).thenReturn(new ArrayList<Rating>());

		List<Rating> test = ratingService.findAllRatings();

		assertThat(test).isEmpty();
	}

	@Test
	void findAllRatings_whenRatingsAreFound_returnListOfRating() {
		when(mockRepository.findAll()).thenReturn(List.of(rating));

		List<Rating> test = ratingService.findAllRatings();

		assertThat(test.size()).isEqualTo(1);
	}

	@Test
	void findAllRatings_whenCalled_useRepository() {
		ratingService.findAllRatings();

		verify(mockRepository, times(1)).findAll();
	}

	@Test
	void findById_whenRatingIsNotFound_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> ratingService.findRatingById(1));
	}

	@Test
	void findById_whenRatingIsFound_returnRating() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(rating));

		Rating test = ratingService.findRatingById(1);

		assertThat(test).isEqualTo(rating);
	}

	@Test
	void findById_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(rating));

		ratingService.findRatingById(1);

		verify(mockRepository, times(1)).findById(1);
	}

	@Test
	void updateRating_whenCalled_returnUpdatedRating() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(rating));
		when(mockRepository.save(rating)).thenReturn(rating);

		Rating test = ratingService.updateRating(1, rating);

		assertThat(test).isEqualTo(rating);
	}

	@Test
	void updateRating_whenRatingDoesntExist_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> ratingService.updateRating(1, rating));
	}

	@Test
	void updateRating_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(rating));

		ratingService.updateRating(1, rating);

		verify(mockRepository, times(1)).findById(1);
		verify(mockRepository, times(1)).save(rating);
	}

	@Test
	void deleteRatingById_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(rating));

		ratingService.deleteRatingById(1);

		verify(mockRepository, times(1)).deleteById(1);
	}

	@Test
	void deleteRatingById_whenRatingNotFound_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> ratingService.deleteRatingById(1));
	}
}
