package com.movie.app.movie.service;

import com.movie.app.movie.entity.enums.CategoryName;
import com.movie.app.movie.entity.model.AddPictureRequest;
import com.movie.app.movie.entity.model.MovieRequest;
import com.movie.app.movie.entity.model.MovieResponse;

import java.util.List;

public interface MovieService {
    MovieResponse addMovie(MovieRequest movieRequest);

    List<MovieResponse> getAllMovies();

    List<MovieResponse> getMoviesByCategoryName(CategoryName categoryName);
    List<MovieResponse> getMoviesByTagName(String tagName);
    MovieResponse addPictureToMetaData(Long movieId, AddPictureRequest addPictureRequest);
}
