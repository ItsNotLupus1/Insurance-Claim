package com.spring.insurance.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.insurance.entity.ClaimModel;
import com.spring.insurance.entity.FileUpload;
import com.spring.insurance.entity.Health;
import com.spring.insurance.entity.HolderClaim;
import com.spring.insurance.repo.ClaimRepository;
import com.spring.insurance.repo.FileRepository;
import com.spring.insurance.repo.HealthRepository;

@Service
@Transactional
public class ClaimService {
	
	@Autowired
	private ClaimRepository claimRepo;
	
	@Autowired
	private HealthRepository healthRepo;
	
	@Autowired
	private FileRepository fileRepo;
	
	public void addClaim(HolderClaim claim) {
		claimRepo.save(claim);
	}
	
	public void addFullClaim(ClaimModel model, MultipartFile[] fileUpload, Long id) {
		
		if (fileUpload != null && fileUpload.length > 0) {
            for (MultipartFile aFile : fileUpload){
                
            	try {
            		String rootpath = System.getProperty("user.home");
            		File dir = new File(rootpath + File.separator + "FileUploadTesting" + File.separator + id);
            		System.out.println("Path " + dir.getAbsolutePath());
            		if(!dir.exists()) {
            			dir.mkdirs();
            		}
            		
            		File serverFile = new File(dir.getAbsolutePath() + File.separator + aFile.getOriginalFilename());
            		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            		stream.write(aFile.getBytes());
            		stream.close();
            		System.out.println("Server File Location = " + serverFile.getAbsolutePath());
            	
	                System.out.println("Saving file: " + aFile.getOriginalFilename());
	                HolderClaim claim = model.getClaim();
	        		Health health = model.getHealth();
	        		
	        		HolderClaim claims = claim;
	        		claims.setName(claims.getName());
	        		claims.setReason(claims.getReason());
	        		claims.setClaimPrice(claims.getClaimPrice());
	        		claims.setStatus(claims.getStatus());
	        		claims.setHolder_id(id);
	        		claimRepo.save(claims);
	        		
	        		Health healths = health;
	        		healths.setAge(healths.getAge());
	        		healths.setExistingIllness(healths.getExistingIllness());
	        		healths.setHeight(healths.getHeight());
	        		healths.setInjury(healths.getInjury());
	        		healths.setWeight(healths.getWeight());
	        		healths.setHolder_id(id);
	        		healthRepo.save(healths);
	        		
	        		FileUpload files = new FileUpload();
	                files.setFileName(aFile.getOriginalFilename());
	                files.setFilepath(serverFile.getAbsolutePath());
	                files.setHolder_id(id);
	                fileRepo.save(files);
	                System.out.println( "You successfully uploaded file=" + aFile.getOriginalFilename());
	            }
            	catch (Exception e) {
            		System.out.println("You failed to upload " + aFile.getOriginalFilename() + " => " + e.getMessage());
				}
            }
        }
	}
	
	
}
