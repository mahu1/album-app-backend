package com.albums.albumappbackend.entity;

import jakarta.persistence.*;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "length")
    private String length;
    @Column(name = "track_number")
    private Long trackNumber;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;

    public Track() {

    }
    public Track(int id, String title, String length, Long trackNumber, Album album) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.trackNumber = trackNumber;
        this.album = album;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Long getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Long trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public boolean isValueMissing() {
        return StringUtils.isEmpty(this.getTitle()) || StringUtils.isEmpty(this.getLength())
                || StringUtils.isEmpty(this.getTrackNumber());
    }
}
