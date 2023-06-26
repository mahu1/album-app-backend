package com.albums.albumappbackend.entity;

import com.albums.albumappbackend.dto.ArtistDto;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String title;
    @OneToMany(mappedBy="artist")
    private Set<Album> albums;

    Artist() {

    }

    public Artist(ArtistDto artistDto) {
        this.id = artistDto.id();
        this.title = artistDto.title();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
