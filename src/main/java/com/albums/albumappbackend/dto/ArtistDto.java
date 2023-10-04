package com.albums.albumappbackend.dto;

import com.albums.albumappbackend.entity.Artist;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import java.util.stream.Collectors;

public record ArtistDto(
        long id,
        @NotBlank
        String title,
        Set<AlbumPlainDto> albums
) {

        public ArtistDto(Artist artist) {
                this(artist.getId(),
                artist.getTitle(),
                artist.getAlbums().stream().map(a -> new AlbumPlainDto(a.getId(), a.getTitle(), a.getArtist().getTitle(), a.getCover(), a.getReleaseDate(), a.getRating())).collect(Collectors.toSet()));
        }

}
