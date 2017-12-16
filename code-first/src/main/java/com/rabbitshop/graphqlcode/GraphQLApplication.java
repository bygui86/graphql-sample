package com.rabbitshop.graphqlcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot will automatically pick GraphQL Java tools up and set up the appropriate handlers to work automatically.
 *
 * By default, this will expose the GraphQL Service on the /graphql endpoint of our application and will accept POST requests containing
 * the GraphQL Payload. This endpoint can be customised in our application.properties file if necessary.
 */
@SpringBootApplication
public class GraphQLApplication {

	public static void main(final String[] args) {

		SpringApplication.run(GraphQLApplication.class, args);
	}
	
}
