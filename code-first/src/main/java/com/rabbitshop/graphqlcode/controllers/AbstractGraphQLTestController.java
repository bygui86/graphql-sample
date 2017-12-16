package com.rabbitshop.graphqlcode.controllers;

import java.util.List;

import javax.annotation.Resource;

import com.rabbitshop.graphqlcode.exceptions.InvalidQuerySyntaxException;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractGraphQLTestController {
	
	@Resource(name = "graphQLclient")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	private GraphQL graphQLclient;
	
	protected ExecutionResult executeQuery(final String query) {

		final ExecutionResult result = getGraphQLclient().execute(query);

		final List<GraphQLError> errors = result.getErrors();
		if (!errors.isEmpty()) {
			final GraphQLError firstError = errors.get(0);
			final String errMsg = new StringBuilder()
					.append("Message: ")
					.append(firstError.getMessage())
					.append("\n")
					.append("Locations: ")
					.append(firstError.getLocations())
					.toString();
			log.error(errMsg);
			throw new InvalidQuerySyntaxException(errMsg);
		}
		
		return result;
	}

}
