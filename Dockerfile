FROM openjdk:11
COPY build/libs/*.jar quote-dictionary.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "quote-dictionary.jar"]
