package com.albums.albumappbackend.dto;

import com.albums.albumappbackend.entity.Album;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

        Integer rating,

        Set<TrackDto> tracks,

        Set<GenreDto> genres

) {

        public AlbumDto(Album album) {
            this(album.getId(),
                 album.getTitle(),
                 new ArtistDto(album.getArtist().getId(), album.getArtist().getTitle(), null),
                 album.getCover(),
                 album.getReleaseDate().toString(),
                 album.getRating(),
                 album.getTracks().stream().map(t -> new TrackDto(t.getId(), t.getTitle(), t.getSeconds(), t.getTrackNumber(), null)).collect(Collectors.toSet()),
                 album.getGenres().stream().map(g -> new GenreDto(g.getId(), g.getTitle(), null)).collect(Collectors.toSet()));
        }

}
