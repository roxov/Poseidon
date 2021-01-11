package com.nnk.springboot.restcontrollers;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;

@RestController
public class CurvePointRestController {
	private static final Logger LOGGER = LogManager.getLogger(CurvePointRestController.class);

	@Autowired
	private CurvePointService curvePointService;

	@GetMapping(value = "/curvePoint")
	public Optional<CurvePoint> findById(@RequestParam Integer id) {
		LOGGER.info("Getting curve points identified by id");
		return curvePointService.findById(id);
	}

	@PostMapping(value = "/curvePoint")
	public CurvePoint addCurvePoint(@RequestBody CurvePoint curvePoint) {
		LOGGER.info("Adding new curve point");
		return curvePointService.addCurvePoint(curvePoint);
	}

	@PutMapping(value = "/curvePoint")
	public CurvePoint updateCurvePoint(@RequestBody CurvePoint curvePoint) {
		LOGGER.info("Updating curve point");
		return curvePointService.updateCurvePoint(curvePoint);
	}

	@DeleteMapping(value = "/curvePoint")
	public void deleteCurvePoint(@RequestParam Integer id) {
		LOGGER.info("Deleting curve point");
		curvePointService.deleteCurvePoint(id);
	}

}
