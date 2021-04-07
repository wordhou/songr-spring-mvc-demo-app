# Songr

A demo application using Spring MVC and Thymeleaf to serve HTML content.

The application currently lists some albums at the `/albums` url.

# Usage

```shell
./gradlew bootRun
```

Will begin serving content at `localhost:8080`.

The program currently expects a postgres database to be running. The username and password are currently set in
the [`application.properties`](src/main/resources/application.properties) file. This is not for production deployments.

There is now one API endpoint that persists user uploaded information. The program also saves user file uploads to the
`uploaded-photos` directory.