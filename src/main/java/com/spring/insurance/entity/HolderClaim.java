package com.spring.insurance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
@EnableAutoConfiguration
public class HolderClaim {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long claim_id;
	
//	@NotEmpty(message = "Please select your valid name from the dropdown")
	private String name;
	
//	@NotEmpty(message = "Please enter your valid reason")
	private String reason;
	
//	@NotNull(message = "Please enter your valid amount")
//	@Min(500)
	private Integer claimPrice;
	
	private String status;
	
	public Long getClaim_id() {
		return claim_id;
	}

	public void setClaim_id(Long claim_id) {
		this.claim_id = claim_id;
	}

	private Long holder_id;

	public HolderClaim(String name, String reason, Integer claimPrice) {
		super();
		this.name = name;
		this.reason = reason;
		this.claimPrice = claimPrice;
	}
	
	public HolderClaim() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getClaimPrice() {
		return claimPrice;
	}

	public void setClaimPrice(Integer claimPrice) {
		this.claimPrice = claimPrice;
	}

	public Long getHolder_id() {
		return holder_id;
	}

	public void setHolder_id(Long holder_id) {
		this.holder_id = holder_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
