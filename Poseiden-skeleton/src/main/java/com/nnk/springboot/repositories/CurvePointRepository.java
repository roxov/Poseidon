package com.nnk.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.CurvePoint;

public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

	List<CurvePoint> findAllByCurveId(Integer curveId);

	void deleteByCurveId(Integer curveId);

}
