package com.spring.insurance.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.insurance.entity.Hospital;
import com.spring.insurance.repo.HospitalRepository;

@Service
@Transactional
public class SearchService {


    @Autowired
    private HospitalRepository repo;
    
//    @Autowired
//    private HolderRepository holRepo;
     
    public List<Hospital> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }
    
//	public List<Hospital> search(String keyword) {
//	return repo.search(keyword);
//}
    
}
