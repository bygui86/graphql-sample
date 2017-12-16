package com.rabbitshop.graphqlcode.configs;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.rabbitshop.graphqlcode.daos.Author;
import com.rabbitshop.graphqlcode.daos.Post;
import com.rabbitshop.graphqlcode.resolvers.GraphQLInjectionResolver;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class GraphQLConfigs {

	/* GraphQL */
	
	/**
	 * Using the code-first technique we are forced to manually invoke the generation of GraphQL schema.
	 * In order to include all functionalities in our schema, we have to provide all resolver in input (fields, queries and mutations)
	 */
	// Multiple injection
	@Bean(name = "graphQLSchema")
	/*
	 * (type = GraphQLInjectionResolver.class) There is no need to specify the type, it will be inferred by the method input
	 * This way also we can inject multiple different beans, otherwise we are forced to have just one input.
	 */
	@Resource
	public GraphQLSchema buildGraphQlSchema(final GraphQLInjectionResolver... graphqlResolverArray) {
		
		return new GraphQLSchemaGenerator()
				.withOperationsFromSingletons((Object[]) graphqlResolverArray)
				.generate();
	}

	/**
	 * Using annotation instead of "the trick of the empty interface".
	 * See {@link com.rabbitshop.graphqlcode.resolvers.GraphQLInjectionResolver}
	 */
	// @Bean(name = "graphQLSchemaNew")
	// @Resource
	// public GraphQLSchema buildGraphQlSchema(final ApplicationContext appCtx) {
	//
	// return new GraphQLSchemaGenerator()
	// .withOperationsFromSingletons(
	// appCtx.getBeansWithAnnotation(GraphQLInjectionQueryResolverAnnotation.class)
	// .values().toArray())
	// .generate();
	// }

	// Single injection
	// @Bean(name = "graphQLSchema")
	// @Resource(name = "authorQueryResolver")
	// public GraphQLSchema buildSchema(final AuthorQueryResolver authorQueryResolver) {
	//
	// return new GraphQLSchemaGenerator()
	// .withOperationsFromSingletons(authorQueryResolver) // Single injection
	// .generate();
	// }
	
	@Bean(name = "graphQLclient")
	@Resource(name = "graphQLSchema")
	public GraphQL testGraphQLclient(final GraphQLSchema graphQLSchema) {
		
		return GraphQL
				.newGraphQL(graphQLSchema)
				.build();
	}

	/* Spring WebMVC */
	
	/**
	 * This is an example of how to forward a URL to another one in the same context.
	 */
	@Bean
	public WebMvcConfigurerAdapter forwardToIndex() {
		
		return new WebMvcConfigurerAdapter() {
			
			@Override
			public void addViewControllers(final ViewControllerRegistry registry) {
				
				registry.addViewController("/").setViewName("forward:/graphiql");
			}
		};
	}
	
	/* These lists are just for test purposes */
	
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
