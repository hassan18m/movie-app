package com.movie.app.movie.controller;

import com.movie.app.exceptions.InvalidInputException;
import com.movie.app.exceptions.NotFoundException;
import com.movie.app.movie.entity.enums.CategoryName;
import com.movie.app.movie.entity.model.MovieRequest;
import com.movie.app.movie.entity.model.MovieResponse;
import com.movie.app.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/category")
    public ResponseEntity<List<MovieResponse>> getMoviesByCategoryName(@RequestParam CategoryName name) {
        return ResponseEntity.ok(movieService.getMoviesByCategoryName(name));
    }

    @GetMapping("/tag")
    public ResponseEntity<?> getMoviesWithTagName(@RequestParam String name) {
        return ResponseEntity.ok(movieService.getMoviesByTagName(name));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMovie(@RequestBody MovieRequest movieRequest) {
        try {
            return ResponseEntity.ok(movieService.addMovie(movieRequest));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
