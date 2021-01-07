package com.nnk.springboot.restcontrollers;

import java.util.List;

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
import com.nnk.springboot.repositories.CurvePointRepository;

@RestController
public class CurvePointRestController {
	private static final Logger LOGGER = LogManager.getLogger(CurvePointRestController.class);

	@Autowired
	private CurvePointRepository curvePointRepository;

	@GetMapping(value = "/curvePoint")
	public List<CurvePoint> findAllByCurveId(@RequestParam Integer curveId) {
		LOGGER.info("Getting curve points identified by curveId");
		return curvePointRepository.findAllByCurveId(curveId);
	}

	@PostMapping(value = "/curvePoint")
	public CurvePoint addCurvePoint(@RequestBody CurvePoint curvePoint) {
		LOGGER.info("Adding new curve point");
		return curvePointRepository.save(curvePoint);
	}

	@PutMapping(value = "/curvePoint")
	public CurvePoint updateCurvePoint(@RequestBody CurvePoint curvePoint) {
		LOGGER.info("Updating curve point");
		return curvePointRepository.save(curvePoint);
	}

	@DeleteMapping(value = "/curvePoint")
	public void deleteCurvePoint(@RequestParam Integer curveId) {
		LOGGER.info("Deleting curve point");
		curvePointRepository.deleteByCurveId(curveId);
	}

}
