package com.movie.app.movie.repository;

import com.movie.app.movie.entity.Movie;
import com.movie.app.movie.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    boolean existsByTagName(String tagName);
    Tag getByTagName(String tagName);
    Optional<Tag> findByTagName(String tagName);
}
