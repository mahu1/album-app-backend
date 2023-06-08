package com.albums.albumappbackend.entity;

import com.albums.albumappbackend.dto.TrackDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tracks")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "length", nullable = false)
    private String length;
    @Column(name = "track_number", nullable = false)
    private Integer trackNumber;
    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;

    public Track() {

    }

    public Track(TrackDto trackDto) {
        this.id = trackDto.getId();
        this.title = trackDto.getTitle();
        this.length = trackDto.getLength();
        this.trackNumber = trackDto.getTrackNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    @JsonIgnore
    public Album getAlbum() {
        return album;
    }

    @JsonIgnore
    public void setAlbum(Album album) {
        this.album = album;
    }

}
