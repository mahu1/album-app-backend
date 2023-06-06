package com.albums.albumappbackend.dto;

import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.entity.Track;

import java.time.LocalDate;
import java.util.Set;


public class AlbumDto {

    private Long id;
    private String title;
    private String artist;
    private String cover;
    private String releaseDate;
    private Set<Track> tracks;

    public AlbumDto() {

    }

    public AlbumDto(Album album) {
        this.id = album.getId();
        this.title = album.getTitle();
        this.artist = album.getArtist();
        this.cover = album.getCover();
        this.releaseDate = album.getReleaseDate().toString();
        this.tracks = album.getTracks();
    }

    public AlbumDto(Long id, String title, String artist, String cover, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.cover = cover;
        this.releaseDate = releaseDate.toString();
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public Set<Track> getTracks() {
        return tracks;
    }
}
