package com.shekhar.notebook.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Note {
	
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String title;

	@NotNull
	@Size(max = 4000)
	private String text;

	@NotNull
	@DateTimeFormat(style = "M-")
	private Date created = new Date();

	@NotNull
	private String tags;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreated() {
		return created;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public String getTags() {
		return tags;
	}
	
}
