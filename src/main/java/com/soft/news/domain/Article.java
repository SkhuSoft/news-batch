package com.soft.news.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

import org.springframework.data.annotation.CreatedDate;

@Data
@Entity
public class Article implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "art_id")
	private int artId;
	@Column(name = "cate_id")
	private int cateId;

	private String name;
	private String subject;
	private String url;
	private String content;

	@CreatedDate
	private Timestamp created;
	@CreatedDate
	private Timestamp updated;

	@PrePersist
	void onCreate() {
		this.setCreated(new Timestamp((new Date()).getTime()));
	}

	@PreUpdate
	void onPersist() {
		this.setUpdated(new Timestamp((new Date()).getTime()));
	}
}
