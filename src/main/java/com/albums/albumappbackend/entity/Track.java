package com.albums.albumappbackend.entity;

import com.albums.albumappbackend.dto.TrackDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "tracks")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "length")
    private String length;
    @Column(name = "track_number")
    private Long trackNumber;
    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    public String getTitle() {
        return title;
    }

    public String getLength() {
        return length;
    }

    public Long getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Long trackNumber) {
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
