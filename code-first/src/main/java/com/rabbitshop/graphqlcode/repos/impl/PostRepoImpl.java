package com.rabbitshop.graphqlcode.repos.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.rabbitshop.graphqlcode.daos.Post;
import com.rabbitshop.graphqlcode.repos.PostRepo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

/**
 * You should also create a LinkRepository class that will neatly isolate the concern of saving and loading links from the storage.
 * This also makes future extensions and refactoring a lot easier.
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component("postRepo")
public class PostRepoImpl implements PostRepo {

	static final long DEFAULT_OFFSET = 0;
	
	/**
	 * PLEASE NOTE: This injection is just for testing purposes!
	 */
	@Resource(name = "postsList")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PRIVATE)
	List<Post> posts;

	@Override
	public List<Post> getAll() {

		log.debug("Get all Posts");

		return getPosts();
	}
	
	@Override
	public List<Post> getRecents(final Integer offset, final Integer count) {

		log.debug("Get recent Posts offset: " + offset + ", count: " + count);

		return getPosts().stream()
				.skip(offset != null ? offset.longValue() : DEFAULT_OFFSET)
				.limit(count != null ? count : getPosts().size())
				.collect(Collectors.toList());
	}

	@Override
	public List<Post> getByAuthor(final String authorId) {
		
		log.debug("Get Posts by Author: " + authorId);
		
		if (StringUtils.isEmpty(authorId)) {
			return new ArrayList<>();
		}

		return posts.stream()
				.filter(post -> authorId.equals(post.getAuthorId()))
				.collect(Collectors.toList());
	}

	@Override
	public Post saveNew(final Post newPost) {

		log.debug("Save new Post: " + newPost);
		
		/*
		 * The random generation should start from 11, because in this example we generated 10 initial posts with
		 * id between 0 and 10 included
		 */
		newPost.setId(newPost.getTitle().replaceAll(" ", "_") + "_" + RandomUtils.nextInt(11, Integer.MAX_VALUE));
		posts.add(0, newPost);
		return newPost;
	}

}
