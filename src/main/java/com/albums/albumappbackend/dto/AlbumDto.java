package com.albums.albumappbackend.dto;

import com.albums.albumappbackend.entity.Album;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;


public record AlbumDto(
        long id,

        @NotBlank
        String title,

        @NotBlank
        String artist,

        @NotBlank
        String cover,

        @NotBlank
        String releaseDate,

        Set<TrackDto> tracks

) {
        public AlbumDto(long id, String title, String artist, String cover, LocalDate releaseDate) {
            this(id, title, artist, cover, releaseDate.toString(), null);
        }

        public AlbumDto(Album album) {
            this(album.getId(), album.getTitle(), album.getArtist(), album.getCover(), album.getReleaseDate().toString(), album.getTracks().stream().map(t -> new TrackDto(t)).collect(Collectors.toSet()));
        }

}
