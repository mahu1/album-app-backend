package com.albums.albumappbackend.entity;

import com.albums.albumappbackend.dto.AlbumDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "artist", nullable = false)
    private String artist;
    @Column(name = "cover", nullable = false)
    private String cover;
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;
    @OneToMany(mappedBy="album")
    private Set<Track> tracks;

    public Album() {

    }
    public Album(AlbumDto albumDto) {
        this.id = albumDto.getId();
        this.title = albumDto.getTitle();
        this.artist = albumDto.getArtist();
        this.cover = albumDto.getCover();
        this.releaseDate = LocalDate.parse(albumDto.getReleaseDate());
        this.tracks = new HashSet<>();
    }

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

}

