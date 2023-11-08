package com.movie.app.user.entity.model;

import com.movie.app.movie.entity.Movie;
import com.movie.app.user.entity.Preferences;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String email;
    private String phoneNumber;
    private List<Movie> favoriteMovies;
    private Preferences preferences;
}
