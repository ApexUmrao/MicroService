#Start with a base image containing Java runtime
FROM eclipse-temurin:21-jdk

# MAINTAINER instruction is deprecated in favor of using label
# MAINTAINER apexUchiha
#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="apexUchiha"

# Add the application's jar to the image
COPY target/Accounts.jar Accounts.jar

# execute the application
ENTRYPOINT ["java", "-jar", "Accounts.jar"]