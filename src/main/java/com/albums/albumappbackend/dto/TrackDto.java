package com.albums.albumappbackend.dto;

import com.albums.albumappbackend.entity.Track;

public class TrackDto {

    private Long id;
    private String title;
    private String length;
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
