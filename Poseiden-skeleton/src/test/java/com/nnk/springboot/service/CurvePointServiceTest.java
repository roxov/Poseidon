package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CurvePointServiceTest {

	@Autowired
	private CurvePointService curvePointService;

	@MockBean
	private CurvePointRepository curvePointRepository;

	@Test
	public void givenACurvePoint_whenAddCurvePoint_thenReturnCreatedCurvePoint() {
		// GIVEN
		CurvePoint curvePoint1 = new CurvePoint(1, 15.2, 5.6);
		when(curvePointRepository.save(curvePoint1)).thenReturn(curvePoint1);

		// WHEN
		CurvePoint result = curvePointService.addCurvePoint(curvePoint1);

		// THEN
		verify(curvePointRepository, Mockito.times(1)).save(any(CurvePoint.class));
		assertEquals(1, result.getCurveId());
		assertEquals(15.2, result.getTerm());
		assertEquals(5.6, result.getValue());
	}

	@Test
	public void givenACurvePoint_whenFindById_thenReturnTheCurvePoint() {
		// GIVEN
		CurvePoint curvePoint = new CurvePoint(1, 2, 15.2, 5.6);
		when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));

		// WHEN
		Optional<CurvePoint> result = curvePointService.findById(1);

		// THEN
		verify(curvePointRepository, Mockito.times(1)).findById(1);
		assertEquals(2, result.get().getCurveId());
		assertEquals(15.2, result.get().getTerm());
		assertEquals(5.6, result.get().getValue());
	}

	@Test
	public void givenACurvePoint_whenUpdateCurvePoint_thenReturnUpdatedCurvePoint() {
		// GIVEN
		CurvePoint curvePoint1 = new CurvePoint(1, 15.2, 5.6);
		when(curvePointRepository.save(curvePoint1)).thenReturn(curvePoint1);

		// WHEN
		CurvePoint result = curvePointService.updateCurvePoint(curvePoint1);

		// THEN
		verify(curvePointRepository, Mockito.times(1)).save(any(CurvePoint.class));
		assertEquals(1, result.getCurveId());
		assertEquals(15.2, result.getTerm());
		assertEquals(5.6, result.getValue());
	}

	@Test
	public void givenACurvePoint_whenDeleteCurvePoint_thenVerifyMethodCalled() {
		// GIVEN
		doNothing().when(curvePointRepository).deleteById(1);

		// WHEN
		curvePointService.deleteCurvePoint(1);

		// THEN
		verify(curvePointRepository, Mockito.times(1)).deleteById(any(Integer.class));
	}
}
