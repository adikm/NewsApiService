FROM java:8
WORKDIR /
ADD target/NewsApiService-1.0-SNAPSHOT-jar-with-dependencies.jar NewsApiService.jar
EXPOSE 7000
CMD java -jar NewsApiService.jar