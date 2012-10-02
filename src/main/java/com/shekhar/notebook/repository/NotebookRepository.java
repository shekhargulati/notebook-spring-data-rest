package com.shekhar.notebook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.repository.annotation.RestResource;

import com.shekhar.notebook.domain.Notebook;

@RestResource(path = "notebooks")
public interface NotebookRepository extends CrudRepository<Notebook, Long> {

	@RestResource(path = "name", rel = "names")
	public Notebook findByNameLike(@Param("name") String name);

}
