package com.spring.insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.insurance.entity.Holder;

@Repository
public interface HolderRepository extends JpaRepository<Holder, Long> {
	
	Holder findByUsername(String username);
	
	Boolean existsByEmail(String email);
	
	Boolean existsByUsername(String username);

}
