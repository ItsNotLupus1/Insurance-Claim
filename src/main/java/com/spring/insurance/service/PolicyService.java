package com.spring.insurance.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.insurance.entity.Policy;
import com.spring.insurance.entity.Insurance;
import com.spring.insurance.repo.InsuranceRepository;
import com.spring.insurance.repo.PolicyRepository;

@Service
@Transactional
public class PolicyService {

	@Autowired
	private PolicyRepository policyRepo;
	
	@Autowired
	private InsuranceRepository insRepo;
	
	public void addPolicy(Policy policy) {
		policyRepo.save(policy);
	}
	
	public List<Policy> getPolicy(){
		return policyRepo.findAll();
	}
	
	public List<Insurance> getAllIns(){
		return insRepo.findAll();
	}
	
	public Policy updatePolicy(Policy policy) {
		Policy policy1 = policy;
		if(policy.getPolicy_id() > 0) {
			Optional<Policy> policy2 = policyRepo.findById(policy.getPolicy_id());
			if(policy2.isPresent()) {
				policy1 = policy2.get();
				policy1.setPolName(policy1.getPolName());
				policy1.setPolNum(policy1.getPolNum());
				policy1.setAssAmount(policy.getAssAmount());
				policy1.setStartDate(policy1.getStartDate());
				policy1.setEndDate(policy1.getEndDate());
				policy1.setHolder(policy1.getHolder());
				policy1.setIns_id(policy1.getIns_id());
			}
		}
		Policy save = policyRepo.save(policy1);
		return save;
	}
}
