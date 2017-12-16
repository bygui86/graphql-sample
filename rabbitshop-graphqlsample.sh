#!bin/sh

# Prerequisites:
#	. Java
#	. Gradle
#	. GraphiQL standalone [optional]
#	. Postman [optional]
#	. Insomnia [optional]

# Compile with Gradle
./gradlew build


# Run the application ...

# ... using Java
java -jar build/libs/rs-graphqlsample-schema-first-0.1.jar
java -jar build/libs/rs-graphqlsample-code-first-0.1.jar

# ... using Spring Boot
./gradlew bootRun


# Test the application ...
# ... see ./resources/graphql/sample-queries.txt for samples

# ... using a CLI
curl http://localhost:8080/graphql?query={recentPosts(offset: 5, count: 3) { title text author{name}}}

# ... using a browser
# http://localhost:8080/graphiql

# ... using standalone GraphiQL application

# ... using Postman

# ... using Insomnia
