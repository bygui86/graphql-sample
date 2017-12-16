package com.rabbitshop.graphqlschema.scalars;

import java.time.Duration;
import java.time.Instant;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

/**
 * TODO
 *
 * This is just an example taken from
 * {@link https://github.com/merapar/graphql-spring-boot-starter/blob/master/graphql-core/src/main/java/com/merapar/graphql/Scalars.java}
 *
 * To know more and try to implement a custom Scalar, please refer somewhere here
 * {@link https://github.com/graphql-java/awesome-graphql-java}
 * for example here
 * {@link https://github.com/donbeave/graphql-java-datetime/tree/master/graphql-datetime-sample-app}
 */
public class TimeScalars {
	
	public final static GraphQLScalarType GraphQlInstant = new GraphQLScalarType("Instant", "Built-in java.time.Instant",
			new Coercing<Object, Instant>() {

				@Override
				public Instant serialize(final Object input) {

					return getInstant(input);
				}

				@Override
				public Instant parseValue(final Object input) {

					return getInstant(input);
				}

				@Override
				public Instant parseLiteral(final Object input) {

					return getInstant(input);
				}

				private Instant getInstant(final Object input) {

					if (input instanceof Instant) {
						return (Instant) input;
					} else if (input instanceof String) {
						return Instant.parse((String) input);
					} else if (input instanceof StringValue) {
						return Instant.parse(((StringValue) input).getValue());
					}

					return null;
				}
			});

	public final static GraphQLScalarType GraphQlDuration = new GraphQLScalarType("Duration", "Built-in java.time.Duration",
			new Coercing<Object, Duration>() {
				
				@Override
				public Duration serialize(final Object input) {
					
					return getDuration(input);
				}
				
				@Override
				public Duration parseValue(final Object input) {
					
					return getDuration(input);
				}
				
				@Override
				public Duration parseLiteral(final Object input) {
					
					return getDuration(input);
				}
				
				private Duration getDuration(final Object input) {
					
					if (input instanceof Duration) {
						return (Duration) input;
					} else if (input instanceof String) {
						return Duration.parse((String) input);
					} else if (input instanceof StringValue) {
						return Duration.parse(((StringValue) input).getValue());
					}
					return null;
				}
			});

}
