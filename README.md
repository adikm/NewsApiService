# NewsService

## Building

To build this application, simply execute:

```shell
mvn install
```

in the project's working directory

## Running

You can run this project by executing packaged jar with dependencies.
Change directory to /target/ and run in command line:
```shell
java -jar NewsApiService-1.0-SNAPSHOT-jar-with-dependencies.jar API_KEY
```

or using Docker:

In the project's working directory execute the following commands:
```
docker build . -t newsapiservice
docker run -p 7000:7000 newsapiservice YOUR_API_KEY
```
where your API_KEY is api key obtained from [NewsAPI.org](https://newsapi.org/register)
. If you don't have one, you can use mine: ```7f0c496e01e6437196c01d9d9380a1f7```

The project will start on port 7000.

## API documentation

After successfully starting the application, API documentation is available on
[http://localhost:7000/apidocs.html](http://localhost:7000/apidocs.html)
