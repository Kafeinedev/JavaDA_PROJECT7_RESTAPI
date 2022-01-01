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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.impl.DefaultCurvePointService;

@ExtendWith(MockitoExtension.class)
class DefaultCurvePointServiceTests {

	@Mock
	private CurvePointRepository mockRepository;

	@InjectMocks
	private DefaultCurvePointService curvePointService;

	private CurvePoint curvePoint;

	@BeforeEach
	void setUp() {
		curvePoint = new CurvePoint(10, 10d, 30d);
	}

	@Test
	void createCurvePoint_whenCalled_returnCreatedCurvePoint() {
		when(mockRepository.save(curvePoint)).thenReturn(curvePoint);

		CurvePoint test = curvePointService.createCurvePoint(curvePoint);

		assertThat(test).isEqualTo(curvePoint);
	}

	@Test
	void createCurvePoint_whenCalled_useRepository() {
		curvePointService.createCurvePoint(curvePoint);

		verify(mockRepository, times(1)).save(curvePoint);
	}

	@Test
	void findAllCurvePoints_whenNoCurvePointsAreFound_returnEmptyList() {
		when(mockRepository.findAll()).thenReturn(new ArrayList<CurvePoint>());

		List<CurvePoint> test = curvePointService.findAllCurvePoints();

		assertThat(test).isEmpty();
	}

	@Test
	void findAllCurvePoints_whenCurvePointsAreFound_returnListOfCurvePoint() {
		when(mockRepository.findAll()).thenReturn(List.of(curvePoint));

		List<CurvePoint> test = curvePointService.findAllCurvePoints();

		assertThat(test.size()).isEqualTo(1);
	}

	@Test
	void findAllCurvePoints_whenCalled_useRepository() {
		curvePointService.findAllCurvePoints();

		verify(mockRepository, times(1)).findAll();
	}

	@Test
	void findById_whenCurvePointIsNotFound_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> curvePointService.findCurvePointById(1));
	}

	@Test
	void findById_whenCurvePointIsFound_returnCurvePoint() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(curvePoint));

		CurvePoint test = curvePointService.findCurvePointById(1);

		assertThat(test).isEqualTo(curvePoint);
	}

	@Test
	void findById_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(curvePoint));

		curvePointService.findCurvePointById(1);

		verify(mockRepository, times(1)).findById(1);
	}

	@Test
	void updateCurvePoint_whenCalled_returnUpdatedCurvePoint() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(curvePoint));
		when(mockRepository.save(curvePoint)).thenReturn(curvePoint);

		CurvePoint test = curvePointService.updateCurvePoint(1, curvePoint);

		assertThat(test).isEqualTo(curvePoint);
	}

	@Test
	void updateCurvePoint_whenCurvePointDoesntExist_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> curvePointService.updateCurvePoint(1, curvePoint));
	}

	@Test
	void updateCurvePoint_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(curvePoint));

		curvePointService.updateCurvePoint(1, curvePoint);

		verify(mockRepository, times(1)).findById(1);
		verify(mockRepository, times(1)).save(curvePoint);
	}

	@Test
	void deleteCurvePointById_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(curvePoint));

		curvePointService.deleteCurvePointById(1);

		verify(mockRepository, times(1)).deleteById(1);
	}

	@Test
	void deleteCurvePointById_whenCurvePointNotFound_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> curvePointService.deleteCurvePointById(1));
	}
}
