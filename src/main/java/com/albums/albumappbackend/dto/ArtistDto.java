package com.albums.albumappbackend.dto;

import com.albums.albumappbackend.entity.Artist;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import java.util.stream.Collectors;

public record ArtistDto(
        long id,
        @NotBlank
        String title,
        Set<AlbumDto> albums
) {

        public ArtistDto(Artist artist) {
                this(artist.getId(),
                artist.getTitle(),
                artist.getAlbums().stream().map(a -> new AlbumDto(a.getId(), a.getTitle(), null, a.getCover(), a.getReleaseDate().toString(), a.getRating(), null, null)).collect(Collectors.toSet()));
        }

}
