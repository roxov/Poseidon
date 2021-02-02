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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;

/**
 * Rest controller for CurvePoint entities.
 *
 */

@RestController
@RequestMapping("rest")
public class CurvePointRestController {

	private static final Logger LOGGER = LogManager.getLogger(CurvePointRestController.class);

	@Autowired
	private CurvePointService curvePointService;

	@PostMapping(value = "/curvePoint")
	public Optional<CurvePoint> addCurvePoint(@RequestBody CurvePoint curvePoint) {
		LOGGER.info("Adding new curve point");
		return Optional.of(curvePointService.addCurvePoint(curvePoint));
	}

	@GetMapping(value = "/curvePoint")
	public Optional<CurvePoint> findById(@RequestParam Integer id) {
		if (id == null) {
			LOGGER.error("The id must be fielded.");
			return Optional.empty();
		}
		LOGGER.info("Getting curve points identified by id");
		return curvePointService.findById(id);
	}

	@PutMapping(value = "/curvePoint")
	public Optional<CurvePoint> updateCurvePoint(@RequestBody CurvePoint curvePoint) {
		if (curvePoint.getId() == null) {
			LOGGER.error("The id is mandatory.");
			return Optional.empty();
		}
		LOGGER.info("Updating curve point");
		return Optional.of(curvePointService.updateCurvePoint(curvePoint));
	}

	@DeleteMapping(value = "/curvePoint")
	public void deleteCurvePoint(@RequestParam Integer id) {
		if (id == null) {
			LOGGER.error("The id must be fielded.");
		} else {
			LOGGER.info("Deleting curve point");
			curvePointService.deleteCurvePoint(id);
		}
	}

}
