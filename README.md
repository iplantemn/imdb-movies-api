# imdb-movies-api
Movies API for the IMDB SmartThings project

## Building

| Operating System | Command           |
| :--------------- | :---------------- |
| macOS            | `./gradlew build` |
| Windows          | `gradlew build`   |

## Deploying to Docker

Once you've built the application, navigate to the root of the project and run one of these commands:
```shell script
docker-compose up --build # build image & launch a containers for database and service
docker-compose up movies-db --build # build image & launch container for database only
```

## API versioning

| Version  | Description |
| -------- | :---------- |
| `api/v1` | Completely implemented with Spring Data REST. |


## TODO
1. ~~Dockerize app and database~~
1. Implement Spring Data REST
1. Insert seed data into database
1. Entity - fetch genres & studio
1. Set to api/v1/xyz
1. Implement Swagger & document
1. Create Postman collection
1. Integration tests
1. Implement linter
1. Jacoco coverage
1. Use Lombok for entity
1. ArchUnit tests