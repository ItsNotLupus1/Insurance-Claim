package com.spring.insurance.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@EnableAutoConfiguration
public class Dependents {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotEmpty(message="Please enter your valid firstname")
	private String firstname;
	
//	@NotEmpty(message="Please enter your valid lastname")
	private String lastname;
	
//	@NotEmpty(message="Please enter your valid relation")
	private String relation;
	
//	@Email(message="Please enter your valid email address")
	private String mail;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@NotNull(message="Please enter your valid date of birth")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private Long holder_id;

	public Dependents(Long id, String firstname, String lastname, String relation, String mail, Date date) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.relation = relation;
		this.mail = mail;
		this.date = date;
	}
	
	public Dependents() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getHolder_id() {
		return holder_id;
	}

	public void setHolder_id(Long holder_id) {
		this.holder_id = holder_id;
	}
	
	

}
