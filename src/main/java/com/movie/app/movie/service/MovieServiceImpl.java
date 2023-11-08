package com.movie.app.movie.service;

import com.movie.app.exceptions.InvalidInputException;
import com.movie.app.exceptions.NotFoundException;
import com.movie.app.globalhelper.MapEntity;
import com.movie.app.movie.entity.*;
import com.movie.app.movie.entity.enums.CategoryName;
import com.movie.app.movie.entity.model.AddPictureRequest;
import com.movie.app.movie.entity.model.MovieRequest;
import com.movie.app.movie.entity.model.MovieResponse;
import com.movie.app.movie.repository.GenreRepository;
import com.movie.app.movie.repository.MovieRepository;
import com.movie.app.movie.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // did not @Autowire here for readability, lombok does this anyway
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final TagRepository tagRepository;
    private final PictureRepository pictureRepository;

    @Override
    public MovieResponse addMovie(MovieRequest movieRequest) {
        Movie movie = movieInit(movieRequest);
        checkInvalidDate(movie.getDateReleased(), movie.getDateAvailableUntil());
        movieRepository.save(movie);

        return MapEntity.movieEntityToResponse(movie);
    }

    @Override
    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(MapEntity::movieEntityToResponse)
                .toList();
    }

    @Override
    public List<MovieResponse> getMoviesByCategoryName(CategoryName categoryName) {
        return movieRepository.getMovieByGenreCategoryName(categoryName)
                .stream()
                .map(MapEntity::movieEntityToResponse)
                .toList();
    }

    @Override
    public List<MovieResponse> getMoviesByTagName(String tagName) {
        Optional<Tag> tagOptional = tagRepository.findByTagName(tagName);
        if (tagOptional.isEmpty()) {
            return new ArrayList<>();
        }

        Tag tag = tagOptional.get();
        List<Movie> moviesWithTag = movieRepository.findAll()
                .stream()
                .filter(movie -> movie.getTags().contains(tag)).toList();

        return moviesWithTag
                .stream()
                .map(MapEntity::movieEntityToResponse)
                .toList();
    }

    @Override
    public MovieResponse addPictureToMetaData(Long movieId, AddPictureRequest addPictureRequest) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundException("Movie not found!"));
        Picture picture = new Picture();
        picture.setUrl(addPictureRequest.getUrl());
        pictureRepository.save(picture);

        Metadata movieMetadata = movie.getMetadata();
        Set<Picture> metadataPictures = movieMetadata.getPictures();
        metadataPictures.add(picture);
        movieMetadata.setPictures(metadataPictures);

        movie.setMetadata(movieMetadata);
        movieRepository.save(movie);

        return MapEntity.movieEntityToResponse(movie);
    }


    private Movie movieInit(MovieRequest movieRequest) {
        Genre genre = genreRepository.findByCategoryName(movieRequest.getCategoryName())
                .orElseThrow(() -> new NotFoundException("Genre not found!"));

        Set<Tag> movieTags = movieRequest.getTagNames().stream().map(tagName -> {
                    if (tagRepository.existsByTagName(tagName)) {
                        return tagRepository.getByTagName(tagName);
                    }
                    Tag tag = new Tag();
                    tag.setTagName(tagName);
                    tagRepository.save(tag);
                    return tag;
                })
                .collect(Collectors.toSet());

        Metadata metadata = new Metadata();
        metadata.setMetaTitle(movieRequest.getMetaTitle());
        metadata.setMetaDescription(movieRequest.getMetaDescription());

        Movie movie = new Movie();
        movie.setTitle(movieRequest.getTitle());
        movie.setGenre(genre);
        movie.setTags(movieTags);
        movie.setLength(movieRequest.getLength());
        movie.setDateReleased(movieRequest.getDateReleased());
        movie.setDateAvailableUntil(movieRequest.getDateAvailableUntil());
        movie.setMetadata(metadata);

        return movie;
    }

    public void checkInvalidDate(LocalDate releaseDate, LocalDate dateUntil) {
        if (dateUntil.isBefore(releaseDate)) {
            throw new InvalidInputException("Available Until Date cannot be before Release Date!");
        }
        if (dateUntil.isBefore(LocalDate.now())) {
            throw new InvalidInputException("Available Until Date cannot be before today!");
        }
    }
}
