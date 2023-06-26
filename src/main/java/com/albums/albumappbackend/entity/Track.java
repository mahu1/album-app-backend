package com.albums.albumappbackend.entity;

import com.albums.albumappbackend.dto.TrackDto;
import jakarta.persistence.*;

@Entity
@Table(name = "tracks")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int seconds;
    @Column(nullable = false)
    private int trackNumber;
    @ManyToOne
    private Album album;

    public Track() {

    }

    public Track(TrackDto trackDto) {
        this.id = trackDto.id();
        this.title = trackDto.title();
        this.seconds = trackDto.seconds();
        this.trackNumber = trackDto.trackNumber();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
