package com.rabbitshop.graphqlcode.daos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Every complex type in the GraphQL server is represented by a Java bean – whether loaded from the root query or from anywhere else in the structure.
 * The same Java class must always represent the same GraphQL type, but the name of the class is not necessary.
 *
 * Fields inside the Java bean will directly map onto fields in the GraphQL response based on the name of the field.
 *
 * Any fields or methods on the Java bean that do not map on to the GraphQL schema will be ignored, but will not cause problems.
 * This is important for field resolvers to work.
 */
@NoArgsConstructor
/*
 * @RequiredArgsConstructor
 * TODO fix the interference of this constructors with Jackson (performing POST requests)
 *		setting default constructor annotation for Jackson?
 *			@AllArgsConstructor(onConstructor = @__({ @JsonCreator })) not working :(
 *			try @PersistenceConstructor
 */
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
	
	@GraphQLQuery
	String id;
	
	@NonNull
	@GraphQLQuery
	String title;

	@NonNull
	@GraphQLQuery
	String text;

	@GraphQLQuery
	String category;
	
	@NonNull
	@GraphQLQuery
	String authorId;
	
}
