package com.rabbitshop.graphqlschema.daos;

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

	String id;

	@NonNull
	String name;

	String thumbnail;

	/**
	 * We don't need to specify anything about Posts, the relation is already in place
	 */
	// private List<Post> posts;
	
}
