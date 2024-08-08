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

    @Column(nullable = false)
    private int discNumber;
    @ManyToOne
    private Album album;

    public Track() {

    }

    public Track(TrackDto trackDto) {
        this.id = trackDto.id();
        this.title = trackDto.title();
        this.seconds = trackDto.seconds();
        this.trackNumber = trackDto.trackNumber();
        this.discNumber = trackDto.discNumber();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public int getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(int discNumber) {
        this.discNumber = discNumber;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
