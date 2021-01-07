package com.nnk.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

	List<Rating> findAllByOrderNumber(Integer orderNumber);

	void deleteByOrderNumber(Integer orderNumber);

}
