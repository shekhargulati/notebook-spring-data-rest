package com.shekhar.notebook.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.repository.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.shekhar.notebook.domain.Note;

@Repository
@RestResource(path = "notes")
public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {

}
