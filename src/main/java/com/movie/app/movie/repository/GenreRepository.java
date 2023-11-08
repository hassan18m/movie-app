package com.movie.app.movie.repository;

import com.movie.app.movie.entity.Genre;
import com.movie.app.movie.entity.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {
    Optional<Genre> findByCategoryName(CategoryName categoryName);
}
