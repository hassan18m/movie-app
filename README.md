# Movie Application
**Note: For app proper functionality all values from CategoryName enum should be inserted into database manually.**

## Showcase

### Endpoint to expose all movies: http://localhost:8080/api/v1/movies/all
![All Movies](src/main/resources/static/gifs/get-all-movies.gif)

### Endpoint to expose movies from a category: http://localhost:8080/api/v1/movies/category?name={categoryName}
![Movies by category](src/main/resources/static/gifs/get-movie-by-category.gif)

### Endpoint to expose movies from a tag: http://localhost:8080/api/v1/movies/tag?name={tagName}
![Movies by tag](src/main/resources/static/gifs/get-movie-by-tag.gif)

### Endpoint to expose all users
![Users](src/main/resources/static/gifs/get-all-users.gif)

### Endpoint to expose user by id (with favourite movie): http://localhost:8080/api/v1/users/{userId}
![User by Id](src/main/resources/static/gifs/get-user-by-id.gif)

## Other endpoints

### Insert user : http://localhost:8080/api/v1/users/add
![Insert user](src/main/resources/static/gifs/insert-user.gif)

### Insert movie : http://localhost:8080/api/v1/movies/add
![Insert movie](src/main/resources/static/gifs/insert-movie.gif)

### Add picture to movie metadata : http://localhost:8080/api/v1/movies/{movieId}/addPicture
![Add picture](src/main/resources/static/gifs/add-picture-to-metadata.png)

### Exception handling examples
![Example 1](src/main/resources/static/gifs/exception1.gif)

![Example 2](src/main/resources/static/gifs/user-exception.gif)
