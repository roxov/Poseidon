package com.nnk.springboot.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointService {
	private static final Logger LOGGER = LogManager.getLogger(CurvePointService.class);

	@Autowired
	private CurvePointRepository curvePointRepository;

	public CurvePoint addCurvePoint(CurvePoint curvePoint) {
		LOGGER.info("Adding new curve point");
		return curvePointRepository.save(curvePoint);
	}

	public Optional<CurvePoint> findById(Integer id) {
		LOGGER.info("Getting curve points identified by id");
		return curvePointRepository.findById(id);
	}

	public CurvePoint updateCurvePoint(CurvePoint curvePoint) {
		LOGGER.info("Updating curve point");
		return curvePointRepository.save(curvePoint);
	}

	public void deleteCurvePoint(Integer id) {
		LOGGER.info("Deleting curve point");
		curvePointRepository.deleteById(id);
	}
}
