package com.albums.albumappbackend.entity;

import com.albums.albumappbackend.dto.AlbumDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String artist;
    @Column(nullable = false)
    private String cover;
    @Column(nullable = false)
    private LocalDate releaseDate;
    @OneToMany(mappedBy="album")
    private Set<Track> tracks;

    Album() {

    }

    public Album(AlbumDto albumDto) {
        this.id = albumDto.id();
        this.title = albumDto.title();
        this.artist = albumDto.artist();
        this.cover = albumDto.cover();
        this.releaseDate = LocalDate.parse(albumDto.releaseDate());
    }

    public long getId() {
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

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }
}

