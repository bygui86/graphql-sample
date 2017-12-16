package com.rabbitshop.graphqlcode.filters;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class AuthorFilter {

	/**
	 * The value of the JSON property annotation must match the definition in the schema
	 */
	@Getter(onMethod = @__({ @JsonProperty("thumbnail_not_null_not_empty") }))
	@GraphQLQuery
	boolean thumbnailNotNullNotEmpty;
	
	@Getter(onMethod = @__({ @JsonProperty("name_contains") }))
	@GraphQLQuery
	String nameContains;

}
