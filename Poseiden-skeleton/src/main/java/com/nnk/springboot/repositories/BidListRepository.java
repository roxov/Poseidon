package com.nnk.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.BidList;

public interface BidListRepository extends JpaRepository<BidList, Integer> {

	List<BidList> findAllByAccount(String account);

	void deleteByAccount(String account);

}
