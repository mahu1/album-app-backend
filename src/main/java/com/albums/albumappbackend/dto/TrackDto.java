package com.albums.albumappbackend.dto;

import com.albums.albumappbackend.entity.Track;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TrackDto {

    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String length;
    @NotNull
    private Integer trackNumber;
    private Long albumId;

    public TrackDto() {

    }

    public TrackDto(Track track) {
        this.id = track.getId();
        this.title = track.getTitle();
        this.length = track.getLength();
        this.trackNumber = track.getTrackNumber();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLength() {
        return length;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public Long getAlbumId() {
        return albumId;
    }
}
