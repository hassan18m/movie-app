package com.movie.app.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movie.app.movie.entity.Genre;
import com.movie.app.user.entity.enums.WebsiteTheme;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "preferences")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,insertable = false,updatable = false)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "preference_genres",
            joinColumns = @JoinColumn(name = "preference_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @Column(name = "website_theme")
    @Enumerated(EnumType.STRING)
    private WebsiteTheme websiteTheme;

    @OneToOne(mappedBy = "preferences")
    @JsonIgnore
    private User user;
}
