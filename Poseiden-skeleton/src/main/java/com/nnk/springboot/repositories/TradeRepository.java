package com.nnk.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer> {
	List<Trade> findAllByAccount(String account);

	void deleteByAccount(String account);
}
