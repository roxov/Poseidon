package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;

/**
 * Application controller for CurvePoint entities.
 *
 */
@Controller
public class CurveController {

	private static final Logger LOGGER = LogManager.getLogger(CurveController.class);

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Autowired
	private CurvePointService curvePointService;

	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		LOGGER.info("Getting the curve points list");
		model.addAttribute("curvePoints", curvePointRepository.findAll());
		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		LOGGER.info("Getting the form to add a curve point");
		return "curvePoint/add";
	}

	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			LOGGER.info("Adding new curve point");
			curvePointService.addCurvePoint(curvePoint);
			model.addAttribute("curvePoints", curvePointRepository.findAll());
			return "redirect:/curvePoint/list";
		}
		return "curvePoint/add";
	}

	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		CurvePoint curvePoint = curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curve point Id:" + id));
		LOGGER.info("Getting the form to update a curve point");
		model.addAttribute("curvePoint", curvePoint);
		return "curvePoint/update";
	}

	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "curvePoint/update";
		}

		curvePoint.setId(id);
		LOGGER.info("Updating curve point");
		curvePointService.addCurvePoint(curvePoint);
		model.addAttribute("curvePoints", curvePointRepository.findAll());
		return "redirect:/curvePoint/list";
	}

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curve point Id:" + id));
		LOGGER.info("Deleting curve point");
		curvePointService.deleteCurvePoint(id);
		model.addAttribute("curvePoints", curvePointRepository.findAll());
		return "redirect:/curvePoint/list";
	}
}
