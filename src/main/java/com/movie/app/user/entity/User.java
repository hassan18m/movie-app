package com.movie.app.user.entity;

import com.movie.app.movie.entity.Movie;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id",unique = true,nullable = false,insertable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email",unique = true,nullable = false)
    private String email;

    @Column(name = "phone_number",unique = true,nullable = false)
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_movies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    Set<Movie> favoriteMovies = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "preference_id",referencedColumnName = "id")
    private Preferences preferences;
}
