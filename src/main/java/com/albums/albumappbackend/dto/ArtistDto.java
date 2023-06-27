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
                this(artist.getId(), artist.getTitle(), artist.getAlbums().stream().map(a -> new AlbumDto(a.getId(), a.getTitle(), a.getArtist(), a.getCover(), a.getReleaseDate())).collect(Collectors.toSet()));
        }

        public ArtistDto(long id, String title) {
                this(id, title, null);
        }

}
