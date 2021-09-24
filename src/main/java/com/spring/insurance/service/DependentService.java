package com.spring.insurance.service;

import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AutoPopulatingList;

import com.spring.insurance.entity.DependentCommand;
import com.spring.insurance.entity.Dependents;
import com.spring.insurance.repo.DependentRepository;


@Service
@Transactional
public class DependentService {
	
	@Autowired
	private DependentRepository repo;
	
	public void dependantsList(DependentCommand dependantCommand)
	{
		AutoPopulatingList<Dependents> dependants = dependantCommand.getDepends();
		
		for (Dependents dependant : dependants) {
			dependant.setHolder_id(dependant.getHolder_id());
			dependant.setFirstname(dependant.getFirstname());
			dependant.setLastname(dependant.getLastname());
			dependant.setDate(dependant.getDate());
			dependant.setMail(dependant.getMail());
			dependant.setRelation(dependant.getRelation());
			repo.save(dependant);
			}
	}
	
	public Optional<Dependents> getNameById(Long id) {
		return repo.findById(id);
	}

}
