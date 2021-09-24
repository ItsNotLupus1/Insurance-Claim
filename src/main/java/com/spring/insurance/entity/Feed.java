package com.spring.insurance.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "feeds")
public class Feed {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@URL
	@NotNull
	private String url;
	
	private String title;
	
	@Temporal(TemporalType.TIME)
	private Date last_update;
	
	@NotNull
	private String feed_name;
	
	private String image;
	
	@OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
	private Set<Item> item = new HashSet<>();
	
	public Feed() {
	
	}

	public Feed(String url, String title) {
		super();
		this.url = url;
		this.title = title;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getLast_update() {
		return last_update;
	}

	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}

	public String getFeed_name() {
		return feed_name;
	}

	public void setFeed_name(String feed_name) {
		this.feed_name = feed_name;
	}
	
	public Set<Item> getItem() {
		return item;
	}

	public void setItem(Set<Item> item) {
		this.item = item;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


}
