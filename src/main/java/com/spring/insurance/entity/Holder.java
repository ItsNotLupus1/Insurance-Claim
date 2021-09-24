package com.spring.insurance.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@EnableAutoConfiguration
public class Holder {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long holder_id;
	
	@Size(max=100)
	@NotEmpty(message="Field must not be empty")
	private String fname, lname, city, state;
	
	@Email(message="Please enter a valid email addresss")
	@NotEmpty(message="Field must not be empty")
	@Column(unique=true, nullable= false)
	private String email;
	
	@Size(min=6, message="Username must be equal or above 7 characters")
	@Column(unique=true, nullable= false)
	private String username;
	
	@Size(min=7, message="Password must be above 7 and below 20 characters")
	@Column(nullable= false)
	private String password;
	
	@NotEmpty
	private String gender;
	
	@Size(min=10, max=10, message="Phone number must have maximum of 10 numbers")
	private String mobile;
	
//	private boolean enabled;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Field must not be empty")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable( name="user_role",
                joinColumns = @JoinColumn(name = "holder_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="holder_id", referencedColumnName="holder_id")
	private List<Dependents> dependents;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="holder_id", referencedColumnName="holder_id")
	private List<HolderClaim> claim;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="holder_id", referencedColumnName="holder_id")
	private List<Health> health;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="holder_id", referencedColumnName="holder_id")
	private List<FileUpload> fileUpload;
	
	@OneToOne(mappedBy = "holder")
	private Policy policy;

	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="holder_id", referencedColumnName="holder_id")
	private List<Posts> posts;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "holder")
	private List<Comments> comments;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "holder")
	private List<Reply> reply;
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public List<Dependents> getDependents() {
		return dependents;
	}

	public void setDependents(List<Dependents> dependents) {
		this.dependents = dependents;
	}

	public List<HolderClaim> getClaim() {
		return claim;
	}

	public void setClaim(List<HolderClaim> claim) {
		this.claim = claim;
	}

	public List<Health> getHealth() {
		return health;
	}

	public void setHealth(List<Health> health) {
		this.health = health;
	}

	public List<FileUpload> getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(List<FileUpload> fileUpload) {
		this.fileUpload = fileUpload;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	
	public Holder(String fname, String lname, String gender, String mobile, String username, String email, String password, String city, String state,
			Date dob) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.mobile = mobile;
		this.username = username;
		this.email = email;
		this.password = password;
		this.city = city;
		this.state = state;
		this.dob = dob;
	}
	
	public Holder() {
		super();
	}

	public Long getHolder_id() {
		return holder_id;
	}

	public void setHolder_id(Long holder_id) {
		this.holder_id = holder_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<Posts> getPosts() {
		return posts;
	}

	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}

//	public boolean isEnabled() {
//		return enabled;
//	}
//
//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}
	
	
}
