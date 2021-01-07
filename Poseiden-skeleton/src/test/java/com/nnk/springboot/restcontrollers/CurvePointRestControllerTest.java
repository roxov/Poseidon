package com.nnk.springboot.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
public class CurvePointRestControllerTest {

	@Autowired
	private CurvePointRestController curvePointRestController;

	@MockBean
	private CurvePointRepository curvePointRepository;

	@Test
	public void givenACurveId_whenFindAllByCurveId_thenReturnListWithTheCurvePoint() {
		// GIVEN
		CurvePoint curvePoint = new CurvePoint(1, 15.2, 5.6);
		List<CurvePoint> curvePointList = new ArrayList<>();
		curvePointList.add(curvePoint);
		when(curvePointRepository.findAllByCurveId(1)).thenReturn(curvePointList);

		// WHEN
		List<CurvePoint> result = curvePointRestController.findAllByCurveId(1);

		// THEN
		verify(curvePointRepository, Mockito.times(1)).findAllByCurveId(1);
		assertEquals(15.2, result.get(0).getTerm());
		assertEquals(5.6, result.get(0).getValue());
	}

	@Test
	public void givenACurvePoint_whenAddCurvePoint_thenReturnCreatedCurvePoint() {
		// GIVEN
		CurvePoint curvePoint1 = new CurvePoint(1, 15.2, 5.6);
		when(curvePointRepository.save(curvePoint1)).thenReturn(curvePoint1);

		// WHEN
		CurvePoint result = curvePointRestController.addCurvePoint(curvePoint1);

		// THEN
		verify(curvePointRepository, Mockito.times(1)).save(any(CurvePoint.class));
		assertEquals(1, result.getCurveId());
		assertEquals(15.2, result.getTerm());
		assertEquals(5.6, result.getValue());
	}

}
