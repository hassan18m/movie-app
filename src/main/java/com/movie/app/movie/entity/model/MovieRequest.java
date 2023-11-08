package com.movie.app.movie.entity.model;

import com.movie.app.movie.entity.enums.CategoryName;
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
public class MovieRequest {
    private String title;
    private CategoryName categoryName;
    private List<String> tagNames;
    private LocalTime length;
    private LocalDate dateReleased;
    private LocalDate dateAvailableUntil;
    private String metaTitle;
    private String metaDescription;
}
