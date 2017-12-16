package com.rabbitshop.graphqlcode.resolvers.queries;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rabbitshop.graphqlcode.annotations.GraphQLInjectionQueryResolverAnnotation;
import com.rabbitshop.graphqlcode.daos.Author;
import com.rabbitshop.graphqlcode.filters.AuthorFilter;
import com.rabbitshop.graphqlcode.repos.AuthorRepo;
import com.rabbitshop.graphqlcode.resolvers.GraphQLInjectionQueryResolver;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
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
@GraphQLInjectionQueryResolverAnnotation
public class AuthorQueryResolver implements GraphQLInjectionQueryResolver {

	@Resource(name = "authorRepo")
	@Getter
	AuthorRepo authorRepo;

	@GraphQLQuery(name = "allAuthors") // Specifying the parameter "name" we are modifying the resulting GraphQL query name
	public List<Author> getAllAuthors() {

		log.debug("Get all Authors");

		return getAuthorRepo().getAll();
	}
	
	/**
	 * The name of the input parameter could not match the definition in the schema
	 */
	@GraphQLQuery
	public List<Author> filterAuthors(
			@GraphQLArgument(name = "filter") final AuthorFilter filter) {

		log.debug("Get Authors filtered by: " + filter);

		return getAuthorRepo().getFilterBy(filter);
	}

}
