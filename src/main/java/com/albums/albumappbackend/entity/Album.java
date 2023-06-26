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
    private String cover;
    @Column(nullable = false)
    private LocalDate releaseDate;
    @OneToMany(mappedBy="album")
    private Set<Track> tracks;
    @ManyToOne
    private Artist artist;

    Album() {

    }

    public Album(AlbumDto albumDto) {
        this.id = albumDto.id();
        this.title = albumDto.title();
        this.cover = albumDto.cover();
        this.releaseDate = LocalDate.parse(albumDto.releaseDate());
        this.artist = new Artist(albumDto.artist());
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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }


}

