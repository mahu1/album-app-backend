package com.albums.albumappbackend.entity;

import jakarta.persistence.*;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "album")
@NamedEntityGraph(
        name = "album.tracks",
        attributeNodes = {
                @NamedAttributeNode("tracks")
        }
)
public class Album
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "artist")
    private String artist;
    @Column(name = "cover")
    private String cover;
    @Column(name = "release_date")
    private Date releaseDate;
    @OneToMany(mappedBy="album", fetch = FetchType.LAZY)
    private Set<Track> tracks;

    public Album() {

    }

    public Album(Long id, String title, String artist, String cover, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.cover = cover;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isValueMissing() {
        return StringUtils.isEmpty(this.getTitle()) || StringUtils.isEmpty(this.getArtist())
                || StringUtils.isEmpty(this.getCover()) || this.getReleaseDate() == null;
    }

}

