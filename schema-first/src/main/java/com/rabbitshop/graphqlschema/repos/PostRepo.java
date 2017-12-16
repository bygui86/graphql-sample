package com.rabbitshop.graphqlschema.repos;

import java.util.List;

import com.rabbitshop.graphqlschema.daos.Post;

public interface PostRepo {
	
	List<Post> getAll();
	
	List<Post> getRecents(final Integer offset, final Integer count);
	
	List<Post> getByAuthor(final String authorId);
	
	Post saveNew(final Post newPost);

}
