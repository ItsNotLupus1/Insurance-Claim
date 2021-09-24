package com.spring.insurance.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.insurance.entity.Health;
import com.spring.insurance.repo.HealthRepository;

@Service
@Transactional
public class HealthService {
	
	@Autowired
	HealthRepository healthRepo;
	
	public void saveHealth(Health health)
	{
		healthRepo.save(health);
	}

}
