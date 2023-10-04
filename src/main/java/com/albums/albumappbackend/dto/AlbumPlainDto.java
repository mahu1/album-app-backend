package com.albums.albumappbackend.dto;

import com.albums.albumappbackend.entity.Album;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AlbumPlainDto(
        long id,

        @NotBlank
        String title,

        @NotBlank
        String artist,

        @NotBlank
        String cover,

        @NotNull
        LocalDate releaseDate,

        Integer rating

) {

        public AlbumPlainDto(Album album) {
            this(album.getId(),
                 album.getTitle(),
                 album.getArtist().getTitle(),
                 album.getCover(),
                 album.getReleaseDate(),
                 album.getRating());
        }

}
