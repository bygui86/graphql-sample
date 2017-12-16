package com.rabbitshop.graphqlschema.daos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class Post {
	
	String id;
	
	@NonNull
	String title;

	@NonNull
	String text;

	String category;

	String authorId;
	
}
