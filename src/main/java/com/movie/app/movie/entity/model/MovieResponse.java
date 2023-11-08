package com.movie.app.movie.entity.model;

import com.movie.app.movie.entity.Genre;
import com.movie.app.movie.entity.Metadata;
import com.movie.app.movie.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private Long id;
    private String title;
    private Genre genre;
    private List<Tag> tags;
    private LocalTime length;
    private LocalDate dateReleased;
    private LocalDate dateAvailableUntil;
    private Metadata metadata;
}
