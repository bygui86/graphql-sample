package com.rabbitshop.graphqlschema.repos;

import java.util.List;
import java.util.Optional;

import com.rabbitshop.graphqlschema.daos.Author;
import com.rabbitshop.graphqlschema.filters.AuthorFilter;

public interface AuthorRepo {

	Optional<Author> getById(final String id);
	
	List<Author> getAll();

	List<Author> getFilterBy(final AuthorFilter filter);
	
}
