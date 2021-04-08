# Songr

A demo application using Spring MVC and Thymeleaf to serve HTML content.

The application currently allows users to add albums, upload album images, edit album data. It also allows users to add
songs to albums and update and edit song information.

# Usage

```shell
./gradlew bootRun
```

Will begin serving content at `localhost:8080`.

The program currently expects a postgres database to be running. The username and password are currently set in
the [`application.properties`](src/main/resources/application.properties) file. This is not for production deployments.

# API endpoints

```
GET /
```
Serves a static homepage.

```
GET /albums
```
Serves a rendered HTML page with a list of albums, as well as a form to add new albums.

```
POST /albums/{id}
```
Adds a new album to the database, and redirects back to the `/albums` endpoint.

```
PUT /albums/{id}
```
Edits the fields of an album, and redirects back to the `/albums` endpoint.

```
GET /albums/{id}
```
Gets an album detail page that lets the user edit the album information, as well as add, remove, and edit the songs attached to the album.

```
POST /albums/{id}/songs
```
Creates a new song in the albums.

```
PUT /albums/{id}/songs/{id}
```
Edits the information attached to a song.

```
DELETE /albums/{id}/songs/{id}
```
Deletes a song.
