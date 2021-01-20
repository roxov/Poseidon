package com.nnk.springboot.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.CurvePoint;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointRestControllerTest {
	@Autowired
	private CurvePointRestController curvePointRestController;

	@Test
	public void givenNullId_whenFindById_thenReturnEmptyOptional() {
		// WHEN
		Optional<CurvePoint> result = curvePointRestController.findById(null);

		// THEN
		assertEquals(Optional.empty(), result);
	}

	@Test
	public void givenANullId_whenUpdateCurvePoint_thenReturnEmptyOptional() {
		// GIVEN
		CurvePoint curvePoint = new CurvePoint(null, 2, 15.2, 5.6);

		// WHEN
		Optional<CurvePoint> result = curvePointRestController.updateCurvePoint(curvePoint);

		// THEN
		assertEquals(Optional.empty(), result);
	}
}
