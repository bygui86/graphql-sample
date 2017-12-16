package com.rabbitshop.graphqlschema.resolvers.mutations;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.rabbitshop.graphqlschema.daos.Post;
import com.rabbitshop.graphqlschema.repos.PostRepo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

/**
 * So far, everything that we have done has been about retrieving data from the server. GraphQL also has the ability to update the data stored on the server, by means of mutations.
 *
 * From the point of view of the code, there is no reason that a Query can’t change data on the server. We could easily write query resolvers that accept arguments, save new data and return those changes. Doing this will cause surprising side effects for the API clients, and is considered bad practice.
 *
 * Instead, Mutations should be used to inform the client that this will cause a change to the data being stored.
 *
 * Mutations are defined in the Java code by using classes that implement GraphQLMutationResolver instead of GraphQLQueryResolver.
 *
 * The return value from a Mutation field is then treated exactly the same as from a Query field, allowing nested values to be retrieved as well.
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component("postMutationResolver")
public class PostMutationResolver implements GraphQLMutationResolver {
	
	@Resource(name = "postRepo")
	@Getter
	PostRepo postRepo;

	/**
	 * A mutation can take just some these input arguments:
	 *	. scalars
	 *	. objects
	 *	. lists
	 */
	public Post writePost(final String title, final String text, final String category, final String authorId) {
		
		log.debug("Write new Post title: " + title + ", text: " + text + ", category: " + category + ", authorId: " + authorId);
		
		return getPostRepo().saveNew(
				Post.builder()
						.title(title)
						.text(text)
						.category(category)
						.authorId(authorId)
						.build());
	}

}
