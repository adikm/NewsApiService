FROM java:8
WORKDIR /
ADD target/NewsApiService-1.0-SNAPSHOT-jar-with-dependencies.jar NewsApiService.jar
EXPOSE 7000
ENTRYPOINT ["java", "-jar", "NewsApiService.jar"]
CMD []