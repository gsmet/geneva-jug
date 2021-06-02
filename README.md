# Geneva JUG Quarkus demo - June 2021

## Branches

This repository contains two branches:

- `main` (<- you are here) - Persistence demo with Hibernate ORM, Hibernate Search, Micrometer and a health check
- [`reactive`](https://github.com/gsmet/geneva-jug/tree/reactive) - A branch where Clément switched the initial step to reactive

## Main branch

### Starting the containers

First, start your containers

PostgreSQL:

```
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name postgresql_quarkus_test -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -p 5432:5432 postgres:13.2
```

Elasticsearch:

```
docker run -it --rm=true --name elasticsearch_quarkus_test -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.10.2
```

### Experimenting with dev mode

Then you can start your application in dev mode:

```shell script
./mvnw compile quarkus:dev
```

Some data will automatically be imported in the database but... we haven't coded anything to index the data in Elasticsearch automatically
(if you are interested, you can see how it can be done in this other demo: https://github.com/gsmet/hibernate-search-demo/blob/master/src/main/java/org/acme/hibernate/search/demo/LibraryResource.java#L31-L41, the model is also slightly more complicated in this other demo).

You can connect to the Dev UI to trigger a reindex:

- Go to http://localhost:8080/q/dev/
- Click on `Indexed entity types` in the Hibernate Search card
- Select the entity type and click `Reindex Entities`

Now, you're all set and all the features at http://localhost:8080/ are working..

### Package the application

You can package the application as a jar with:

```shell script
./mvnw clean package
```

And start it with:

```shell script
java -jar target/quarkus-app/quarkus-run.jar
```

> Note that the full application is the entire `target/quarkus-app/` directory.

You have some example Dockerfiles in `src/main/docker`.

### Going native

You can stop the app and compile to native with:

```shell script
./mvnw clean install -Dnative
```

Now you can grab some coffee.

And, once it's done, start the native executable with:

```shell script
./target/rock-rock-rock-1.0.0-SNAPSHOT-runner
```
