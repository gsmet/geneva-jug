# Geneva JUG Quarkus demo - June 2021 - Reactive

## Branches

This repository contains two branches:

- [`main`](https://github.com/gsmet/geneva-jug) - Persistence demo with Hibernate ORM, Hibernate Search, Micrometer and a health check
- `reactive` (<- you are here) - A branch where Clément switched the initial step to reactive

## Reactive branch

This part of the demo uses DevServices so you don't need to start a container.

Just run:

```
./mvnw clean quarkus:dev
```

And go to http://localhost:8080/.

The `Search` part of the UI won't work here as not implemented.
