package com.albums.albumappbackend.dto;

import com.albums.albumappbackend.entity.Track;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TrackDto(

        long id,

        @NotBlank
        String title,

        @Min(0)
        int seconds,

        @Min(1)
        int trackNumber,

        @Min(1)
        long albumId

) {
        public TrackDto(Track track) {
                this(track.getId(), track.getTitle(), track.getSeconds(), track.getTrackNumber(), track.getAlbum().getId());
        }

}
