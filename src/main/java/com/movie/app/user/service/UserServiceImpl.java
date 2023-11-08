package com.movie.app.user.service;

import com.movie.app.exceptions.DuplicateDataException;
import com.movie.app.exceptions.NotFoundException;
import com.movie.app.globalhelper.MapEntity;
import com.movie.app.movie.entity.Genre;
import com.movie.app.movie.entity.Movie;
import com.movie.app.movie.repository.GenreRepository;
import com.movie.app.movie.repository.MovieRepository;
import com.movie.app.user.entity.Preferences;
import com.movie.app.user.entity.User;
import com.movie.app.user.entity.enums.WebsiteTheme;
import com.movie.app.user.entity.model.UserRequest;
import com.movie.app.user.entity.model.UserResponse;
import com.movie.app.user.repository.PreferencesRepository;
import com.movie.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // same as MovieService (readability)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PreferencesRepository preferencesRepository;
    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new DuplicateDataException("Email already used!");
        }
        if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            throw new DuplicateDataException("Phone number already used!");
        }

        User user = userInit(userRequest);
        userRepository.save(user);

        return MapEntity.userEntityToResponse(user);
    }

    @Override
    public UserResponse addFavouriteMovieToUser(UUID userId, Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundException("Movie not found!"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found!"));

        Set<Movie> userFavouriteMovies = user.getFavoriteMovies();
        userFavouriteMovies.add(movie);
        user.setFavoriteMovies(userFavouriteMovies);
        userRepository.save(user);

        return MapEntity.userEntityToResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(MapEntity::userEntityToResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(MapEntity::userEntityToResponse)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }


    private User userInit(UserRequest userRequest) {
        Set<Genre> userFavouritesGenres = userRequest.getFavoriteCategories().stream().map(categoryName -> {
            Optional<Genre> genre = genreRepository.findByCategoryName(categoryName);
            if (genre.isPresent()) {
                return genre.get();
            }
            throw new NotFoundException("Genre not found!");
        }).collect(Collectors.toSet());

        Preferences preferences = new Preferences();
        preferences.setWebsiteTheme(WebsiteTheme.DEVICE_THEME);
        preferences.setGenres(userFavouritesGenres);
        preferencesRepository.save(preferences);

        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setPreferences(preferences);

        return user;
    }
}
