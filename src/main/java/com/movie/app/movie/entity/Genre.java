package com.movie.app.movie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movie.app.movie.entity.enums.CategoryName;
import com.movie.app.user.entity.Preferences;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true, insertable = false)
    private Long id;

    @Column(name = "category_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;

    @OneToMany(mappedBy = "genre")
    @JsonIgnore
    private Set<Movie> movies = new HashSet<>();

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private Set<Preferences> preferences = new HashSet<>();
}
