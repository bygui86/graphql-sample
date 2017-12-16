package com.rabbitshop.graphqlcode.controllers.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rabbitshop.graphqlcode.controllers.AbstractGraphQLTestController;

import graphql.ExecutionResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(path = "/tests/author")
class AuthorTestController extends AbstractGraphQLTestController {
	
	private static final String ALL_AUTHORS_QUERY = "{ allAuthors { id name thumbnail posts { title } } }";
	private static final String FILTER_AUTHORS_QUERY = "{ filterAuthors(filter: { thumbnail_not_null_not_empty: true }) { id name thumbnail } }";

	@GetMapping(path = "/all")
	@ResponseBody
	public ExecutionResult getAllAuthorsTest() {

		log.info("Get all authors test");

		return executeQuery(ALL_AUTHORS_QUERY);
	}

	@GetMapping(path = "/filter")
	@ResponseBody
	public ExecutionResult getFilterAuthorsTest() {

		log.info("Get filter authors test");
		
		return executeQuery(FILTER_AUTHORS_QUERY);
	}

}
