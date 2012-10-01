package com.shekhar.notebook.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Notebook {

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private Long version;

	@NotNull
	private String name;

	@NotNull
	@Size(max = 4000)
	private String description;

	@NotNull
	@DateTimeFormat(style = "M-")
	private Date created = new Date();

	@NotNull
	private String author;

	@NotNull
	private String tags;

	@OneToMany
	private List<Note> notes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getTags() {
		return tags;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

}
