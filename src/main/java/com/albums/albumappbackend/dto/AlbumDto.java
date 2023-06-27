package com.albums.albumappbackend.dto;

import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.entity.Artist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;


public record AlbumDto(
        long id,

        @NotBlank
        String title,

        @NotNull
        ArtistDto artist,

        @NotBlank
        String cover,

        @NotBlank
        String releaseDate,

        Set<TrackDto> tracks

) {
        public AlbumDto(long id, String title, Artist artist, String cover, LocalDate releaseDate) {
            this(id, title, new ArtistDto(artist.getId(), artist.getTitle()), cover, releaseDate.toString(), null);
        }

        public AlbumDto(Album album) {
            this(album.getId(), album.getTitle(), new ArtistDto(album.getArtist()), album.getCover(), album.getReleaseDate().toString(), album.getTracks().stream().map(t -> new TrackDto(t)).collect(Collectors.toSet()));
        }

}
