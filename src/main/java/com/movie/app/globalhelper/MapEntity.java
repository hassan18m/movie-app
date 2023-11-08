package com.movie.app.globalhelper;

import com.movie.app.movie.entity.Movie;
import com.movie.app.movie.entity.model.MovieResponse;
import com.movie.app.user.entity.User;
import com.movie.app.user.entity.model.UserRequest;
import com.movie.app.user.entity.model.UserResponse;

public class MapEntity {
    public static UserResponse userEntityToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setFavoriteMovies(user.getFavoriteMovies().stream().toList());
        userResponse.setPreferences(user.getPreferences());

        return userResponse;
    }

    public static MovieResponse movieEntityToResponse(Movie movie) {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setId(movie.getId());
        movieResponse.setTitle(movie.getTitle());
        movieResponse.setGenre(movie.getGenre());
        movieResponse.setTags(movie.getTags().stream().toList());
        movieResponse.setLength(movie.getLength());
        movieResponse.setDateReleased(movie.getDateReleased());
        movieResponse.setDateAvailableUntil(movie.getDateAvailableUntil());
        movieResponse.setMetadata(movie.getMetadata());

        return movieResponse;
    }
}
