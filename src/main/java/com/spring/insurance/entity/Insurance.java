package com.spring.insurance.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
@EnableAutoConfiguration
public class Insurance {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long ins_id;
	
	private String ins_name;
	
	private String ins_address;
		
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="ins_id", referencedColumnName="ins_id")
	private List<Policy> policy;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name="ins_hosp",
                joinColumns = @JoinColumn(name = "ins_id"),
                inverseJoinColumns = @JoinColumn(name = "hosp_id"))
    private List<Hospital> hospital;

	public Insurance(Long ins_id, String ins_name, String ins_address, List<Policy> policy, List<Hospital> hospital) {
		super();
		this.ins_id = ins_id;
		this.ins_name = ins_name;
		this.ins_address = ins_address;
		this.policy = policy;
		this.hospital = hospital;
	}
	
	public Insurance() {
		super();
	}

	public List<Hospital> getHospital() {
		return hospital;
	}

	public void setHospital(List<Hospital> hospital) {
		this.hospital = hospital;
	}

	public Long getIns_id() {
		return ins_id;
	}

	public void setIns_id(Long ins_id) {
		this.ins_id = ins_id;
	}

	public String getIns_name() {
		return ins_name;
	}

	public void setIns_name(String ins_name) {
		this.ins_name = ins_name;
	}

	public String getIns_address() {
		return ins_address;
	}

	public void setIns_address(String ins_address) {
		this.ins_address = ins_address;
	}
	
	
}
