package com.movie.app.movie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movie.app.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToMany
    @JoinTable(
            name = "movie_tags",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @Column(name = "length", nullable = false)
    private LocalTime length;

    @Column(name = "date_released", nullable = false)
    private LocalDate dateReleased;

    @Column(name = "date_available_until", nullable = false)
    private LocalDate dateAvailableUntil;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "metadata_id", referencedColumnName = "id")
    private Metadata metadata;

    @ManyToMany(mappedBy = "favoriteMovies")
    @JsonIgnore
    private Set<User> users = new HashSet<>();
}
