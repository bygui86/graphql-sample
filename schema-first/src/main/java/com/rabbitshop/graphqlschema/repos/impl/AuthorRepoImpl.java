package com.rabbitshop.graphqlschema.repos.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.rabbitshop.graphqlschema.daos.Author;
import com.rabbitshop.graphqlschema.filters.AuthorFilter;
import com.rabbitshop.graphqlschema.repos.AuthorRepo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

/**
 * The fact that these field resolvers are loaded from the Spring context is important. This allows them to work with any other Spring managed beans – e.g., DAOs.
 *
 * Importantly, if the client does not request a field, then the GraphQL Server will never do the work to retrieve it. This means that if a client retrieves a Post
 * and does not ask for the Author, then the getAuthor() method above will never be executed, and the DAO call will never be made.
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component("authorRepo")
public class AuthorRepoImpl implements AuthorRepo {
	
	/**
	 * PLEASE NOTE: This injection is just for testing purposes!
	 */
	@Resource(name = "authorsList")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PRIVATE)
	List<Author> authors;
	
	/**
	 * The GraphQL Schema has the concept that some types are nullable and others are not.
	 *
	 * This can be handled in the Java code by directly using null values, but equally, the new Optional type from Java 8 can be used directly here for nullable types,
	 * and the system will do the correct thing with the values.
	 *
	 * This is very useful as it means that our Java code is more obviously the same as the GraphQL schema from the method definitions.
	 */
	@Override
	public Optional<Author> getById(final String id) {
		
		log.debug("Get Author by Id: " + id);
		
		if (StringUtils.isEmpty(id)) {
			return Optional.empty();
		}
		
		return getAuthors().stream()
				.filter(a -> id.equals(a.getId()))
				.findFirst();
	}

	@Override
	public List<Author> getAll() {
		
		log.debug("Get all Authors");

		return getAuthors();
	}
	
	@Override
	public List<Author> getFilterBy(final AuthorFilter filter) {
		
		log.debug("Get Authors filtered by: " + filter);
		
		return getAuthors().stream()
				.filter(buildStreamFilter(filter))
				.collect(Collectors.toList());
	}
	
	protected Predicate<? super Author> buildStreamFilter(final AuthorFilter filter) {
		
		if (filter.isThumbnailNotNullNotEmpty()) {
			return a -> a.getThumbnail() != null;
		}
		return a -> true;
	}

}
