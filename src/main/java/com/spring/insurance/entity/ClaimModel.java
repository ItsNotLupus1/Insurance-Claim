package com.spring.insurance.entity;

public class ClaimModel {
	
	private HolderClaim claim;
	
	private Health health;
	
	private FileUpload file;

	public HolderClaim getClaim() {
		return claim;
	}

	public void setClaim(HolderClaim claim) {
		this.claim = claim;
	}

	public Health getHealth() {
		return health;
	}

	public void setHealth(Health health) {
		this.health = health;
	}

	public FileUpload getFile() {
		return file;
	}

	public void setFile(FileUpload file) {
		this.file = file;
	}
	
	

}
