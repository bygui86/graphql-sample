package com.rabbitshop.graphqlcode.controllers.impl;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rabbitshop.graphqlcode.controllers.AbstractGraphQLTestController;
import com.rabbitshop.graphqlcode.daos.Post;

import graphql.ExecutionResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(path = "/tests/posts")
class PostTestController extends AbstractGraphQLTestController {

	private static final String ALL_POSTS_QUERY = "{ allPosts { title text author { name } } }";
	
	@GetMapping(path = "/all")
	@ResponseBody
	public ExecutionResult getAllPostsTest() {
		
		log.info("Get all posts test");
		
		return executeQuery(ALL_POSTS_QUERY);
	}
	
	@GetMapping(path = "/recent")
	@ResponseBody
	public ExecutionResult getRecentPostsTest(
			@PathParam(value = "offset") final int offset,
			@PathParam(value = "count") final int count) {
		
		log.info("Get recent posts test");

		return executeQuery(getRecentPostQuery(offset, count));
	}

	@PostMapping(path = "/new")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ExecutionResult postNewPostsTest(
			@RequestBody final Post post) {
		
		log.info("Post new post test: " + post);

		return executeQuery(getNewPostMutation(post));
	}
	
	private String getRecentPostQuery(final int offset, final int count) {
		
		return new StringBuilder()
				.append("{ recentPosts(offset: ")
				.append(offset)
				.append(", count: ")
				.append(count)
				.append(") { final title text author { name } } }")
				.toString();
	}

	private String getNewPostMutation(final Post post) {

		return new StringBuilder()
				.append("mutation { writePost(title: \"")
				.append(post.getTitle())
				.append("\", text: \"")
				.append(post.getText())
				.append("\", authorId: \"")
				.append(post.getAuthorId())
				.append("\") { title text author { name } } }")
				.toString();
	}

}
