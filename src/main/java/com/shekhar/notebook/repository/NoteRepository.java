package com.shekhar.notebook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.repository.annotation.RestResource;

import com.shekhar.notebook.domain.Note;

@RestResource(path = "notes")
public interface NoteRepository extends CrudRepository<Note, Long> {

	@RestResource(path = "title", rel = "titles")
	public Note findByTitleLike(@Param("title") String title);
}
