package com.albums.albumappbackend.dto;

import com.albums.albumappbackend.entity.Genre;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import java.util.stream.Collectors;

public record GenreDto(

        long id,

        @NotBlank
        String title,

        Set<AlbumDto> albums

) {
        public GenreDto(Genre genre) {
                this(genre.getId(),
                genre.getTitle(),
                genre.getAlbums().stream().map(a -> new AlbumDto(a.getId(), a.getTitle(), null, a.getCover(), a.getReleaseDate().toString(), a.getRating(), null, null)).collect(Collectors.toSet()));
        }

}
