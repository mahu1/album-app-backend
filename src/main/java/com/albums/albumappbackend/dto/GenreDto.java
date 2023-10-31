package com.albums.albumappbackend.dto;

import com.albums.albumappbackend.entity.Genre;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import java.util.stream.Collectors;

public record GenreDto(

        long id,

        @NotBlank
        String title,

        Set<AlbumPlainDto> albums

) {

        public GenreDto(Genre genre) {
                this(genre.getId(),
                genre.getTitle(),
                genre.getAlbums().stream().map(a -> new AlbumPlainDto(a.getId(), a.getTitle(), a.getArtist().getTitle(), a.getCover(), a.getReleaseDate(), a.getRating())).collect(Collectors.toSet()));
        }

}
