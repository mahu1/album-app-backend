package com.albums.albumappbackend.entity;

import com.albums.albumappbackend.dto.AlbumDto;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "albums")

public class Album
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "artist")
    private String artist;
    @Column(name = "cover")
    private String cover;
    @Column(name = "release_date")
    private Date releaseDate;
    @OneToMany(mappedBy="album")
    private Set<Track> tracks;

    public Album() {

    }
    public Album(AlbumDto albumDto) {
        this.id = albumDto.getId();
        this.title = albumDto.getTitle();
        this.artist = albumDto.getArtist();
        this.cover = albumDto.getCover();
        this.releaseDate = albumDto.getReleaseDate();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getCover() {
        return cover;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Set<Track> getTracks() {
        return tracks;
    }


}

