package com.albums.albumappbackend.entity;

import com.albums.albumappbackend.dto.GenreDto;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String title;

    @ManyToMany(mappedBy = "genres")
    private Set<Album> albums;

    public Genre() {

    }

    public Genre(GenreDto genreDto) {
        this.id = genreDto.id();
        this.title = genreDto.title();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
