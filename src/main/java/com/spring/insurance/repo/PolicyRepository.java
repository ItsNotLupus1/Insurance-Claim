package com.spring.insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.insurance.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

}
