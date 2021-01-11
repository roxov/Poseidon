package com.nnk.springboot.service;

import java.util.Optional;

import com.nnk.springboot.domain.CurvePoint;

/**
 * 
 * Manage the CRUD operations concerning curve points.
 *
 */
public interface ICurvePointService {
	public CurvePoint addCurvePoint(CurvePoint curvePoint);

	public Optional<CurvePoint> findById(Integer id);

	public CurvePoint updateCurvePoint(CurvePoint curvePoint);

	public void deleteCurvePoint(Integer id);
}
