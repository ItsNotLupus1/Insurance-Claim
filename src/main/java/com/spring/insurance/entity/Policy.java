package com.spring.insurance.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@EnableAutoConfiguration
public class Policy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long policy_id;
	
	private String polNum;
	private String polName;
	
	private Integer assAmount;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@OneToOne()
	@JoinColumn(name="holder_id", referencedColumnName="holder_id")
	private Holder holder;
	
	private Long ins_id;

	public Policy(Long policy_id, String polNum, String polName, Integer assAmount, Date startDate,
			Date endDate, Holder holder) {
		super();
		this.policy_id = policy_id;
		this.polNum = polNum;
		this.polName = polName;
		this.assAmount = assAmount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.holder = holder;
	}

	


	public Policy() {
		
	}

	public Long getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Long policy_id) {
		this.policy_id = policy_id;
	}

	public String getPolNum() {
		return polNum;
	}

	public void setPolNum(String polNum) {
		this.polNum = polNum;
	}

	public String getPolName() {
		return polName;
	}

	public void setPolName(String polName) {
		this.polName = polName;
	}

	public Integer getAssAmount() {
		return assAmount;
	}

	public void setAssAmount(Integer assAmount) {
		this.assAmount = assAmount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Holder getHolder() {
		return holder;
	}

	public void setHolder(Holder holder) {
		this.holder = holder;
	}

	public Long getIns_id() {
		return ins_id;
	}

	public void setIns_id(Long ins_id) {
		this.ins_id = ins_id;
	}

}
