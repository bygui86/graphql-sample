package com.rabbitshop.graphqlcode.daos;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class Author {
	
	@GraphQLQuery
	String id;
	
	@NonNull
	@GraphQLQuery(name = "name") // The name parameter its optional and can be specified to modify the GraphQL name of the field
	String name;
	
	@GraphQLQuery
	String thumbnail;
	
	/**
	 * We don't need to specify anything about Posts, the relation is already in place
	 */
	// private List<Post> posts;

}
