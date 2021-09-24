package com.spring.insurance.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reply_id;
	
	@Column(name = "reply_statement")
	private String reply_sen;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "holder", nullable= false)
	private Holder holder;

	private Long post_id;
	
	private Long comment_id;

	public Long getReply_id() {
		return reply_id;
	}

	public void setReply_id(Long reply_id) {
		this.reply_id = reply_id;
	}

	public String getReply_sen() {
		return reply_sen;
	}

	public void setReply_sen(String reply_sen) {
		this.reply_sen = reply_sen;
	}

	public Holder getHolder() {
		return holder;
	}

	public void setHolder(Holder holder) {
		this.holder = holder;
	}

	public Long getPost_id() {
		return post_id;
	}

	public void setPost_id(Long post_id) {
		this.post_id = post_id;
	}

	public Long getComment_id() {
		return comment_id;
	}

	public void setComment_id(Long comment_id) {
		this.comment_id = comment_id;
	}
	
	

}
