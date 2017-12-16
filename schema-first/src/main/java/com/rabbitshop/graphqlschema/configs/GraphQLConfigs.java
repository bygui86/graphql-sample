package com.rabbitshop.graphqlschema.configs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitshop.graphqlschema.daos.Author;
import com.rabbitshop.graphqlschema.daos.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class GraphQLConfigs {
	
	@Bean(name = "authorsList")
	public List<Author> authorDao() {
		
		log.debug("Building Authors list...");
		
		final List<Author> authors = new ArrayList<>();
		for (int authorId = 0; authorId < 10; ++authorId) {

			String thumbnail = null;
			if (authorId % 2 == 0) {
				thumbnail = "http://example.com/authors/" + authorId;
			}

			authors.add(
					Author.builder()
							.id("author_" + authorId)
							.name("Author " + authorId)
							.thumbnail(thumbnail)
							.build());
		}
		
		return authors;
	}

	@Bean(name = "postsList")
	public List<Post> postDao() {
		
		log.debug("Building Posts list...");
		
		final List<Post> posts = new ArrayList<>();
		for (int postId = 0; postId < 10; ++postId) {
			posts.add(
					Post.builder()
							.id("Post_" + postId)
							.title("Post " + postId)
							.text("Post " + postId + " text")
							.authorId("author_" + postId)
							.build());
		}
		
		return posts;
	}

}
