package com.movie.app.user.service;

import com.movie.app.user.entity.model.UserRequest;
import com.movie.app.user.entity.model.UserResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserResponse addUser(UserRequest userRequest);

    UserResponse addFavouriteMovieToUser(UUID userId, Long movieId);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(UUID userId);
}
