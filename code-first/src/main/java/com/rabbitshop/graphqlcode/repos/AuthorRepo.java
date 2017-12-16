package com.rabbitshop.graphqlcode.repos;

import java.util.List;
import java.util.Optional;

import com.rabbitshop.graphqlcode.daos.Author;
import com.rabbitshop.graphqlcode.filters.AuthorFilter;

public interface AuthorRepo {

	Optional<Author> getById(final String id);
	
	List<Author> getAll();

	List<Author> getFilterBy(final AuthorFilter filter);
	
}
