package com.nnk.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.nnk.springboot.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
	User findByUsername(String username);

	@Query(" select u from User u " + " where u.username = ?1")
	Optional<User> findUserWithName(String username);

	void deleteByUsername(String username);
}
