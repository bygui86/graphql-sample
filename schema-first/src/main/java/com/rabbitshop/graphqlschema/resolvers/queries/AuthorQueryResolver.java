package com.rabbitshop.graphqlschema.resolvers.queries;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.rabbitshop.graphqlschema.daos.Author;
import com.rabbitshop.graphqlschema.filters.AuthorFilter;
import com.rabbitshop.graphqlschema.repos.AuthorRepo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

/**
 * The root query needs to have special beans defined in the Spring context to handle the various fields in this root query. Unlike the schema definition, there is no
 * restriction that there only be a single Spring bean for the root query fields.
 *
 * The only requirements are that the beans implement GraphQLQueryResolver and that every field in the root query from the scheme has a method in one of these classes
 * with the same name.
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component("authorQueryResolver")
public class AuthorQueryResolver implements GraphQLQueryResolver {

	@Resource(name = "authorRepo")
	@Getter
	AuthorRepo authorRepo;

	public List<Author> getAllAuthors() {
		
		log.debug("Get all Authors");
		
		return getAuthorRepo().getAll();
	}
	
	/**
	 * The name of the input parameter could not match the definition in the schema
	 */
	public List<Author> filterAuthors(final AuthorFilter filter) {
		
		log.debug("Get Authors filtered by: " + filter);
		
		// TODO null check on filter?

		return getAuthorRepo().getFilterBy(filter);
	}
	
}
