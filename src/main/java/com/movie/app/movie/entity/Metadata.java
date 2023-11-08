package com.movie.app.movie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "metadatas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @OneToMany(mappedBy = "metadata")
    private Set<Picture> pictures = new HashSet<>();

    @Column(name = "meta_title", nullable = false, columnDefinition = "TEXT")
    private String metaTitle;

    @Column(name = "meta_description", nullable = false, columnDefinition = "TEXT")
    private String metaDescription;

    @OneToOne(mappedBy = "metadata")
    @JsonIgnore
    private Movie movie;
}
