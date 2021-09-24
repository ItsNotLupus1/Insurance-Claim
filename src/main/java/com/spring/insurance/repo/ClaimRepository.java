package com.spring.insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.insurance.entity.HolderClaim;

public interface ClaimRepository extends JpaRepository<HolderClaim, Long> {

}
