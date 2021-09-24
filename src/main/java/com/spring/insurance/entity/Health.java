package com.spring.insurance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
@EnableAutoConfiguration
public class Health {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long healthid;
	
//	@NotNull(message="Please enter your valid age")
	private Integer Age;
	
//	@NotNull(message="Please enter your valid height")
	private Integer Height;
	
//	@NotNull(message="Please enter your valid weight")
	private Integer Weight;
	
//	@NotEmpty(message="Please enter your illness")
	private String ExistingIllness;
	
//	@NotEmpty(message="Please enter your injury")
	private String Injury;
	
	private Long holder_id;

	public Long getHealthid() {
		return healthid;
	}

	public void setHealthid(Long healthid) {
		this.healthid = healthid;
	}

	public Integer getAge() {
		return Age;
	}

	public void setAge(Integer age) {
		Age = age;
	}

	public Integer getHeight() {
		return Height;
	}

	public void setHeight(Integer height) {
		Height = height;
	}

	public Integer getWeight() {
		return Weight;
	}

	public void setWeight(Integer weight) {
		Weight = weight;
	}

	public String getExistingIllness() {
		return ExistingIllness;
	}

	public void setExistingIllness(String existingIllness) {
		ExistingIllness = existingIllness;
	}

	public String getInjury() {
		return Injury;
	}

	public void setInjury(String injury) {
		Injury = injury;
	}

	public Long getHolder_id() {
		return holder_id;
	}

	public void setHolder_id(Long holder_id) {
		this.holder_id = holder_id;
	}
}
