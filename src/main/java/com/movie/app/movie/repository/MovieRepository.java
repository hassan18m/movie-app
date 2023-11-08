package com.movie.app.movie.repository;

import com.movie.app.movie.entity.Movie;
import com.movie.app.movie.entity.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> getMovieByGenreCategoryName(CategoryName categoryName);
}
