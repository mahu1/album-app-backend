package com.albums.albumappbackend.dao;

import com.albums.albumappbackend.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumDao extends JpaRepository<Album, Long>  {
    @Query("SELECT a FROM Album a " +
            "WHERE " +
            "   LOWER(a.artist) = LOWER(:artist) AND " +
            "   LOWER(a.title) = LOWER(:title) " +
            "ORDER BY a.releaseDate")
    public List<Album> findByArtistAndTitle(String artist, String title);

    @Query("SELECT a FROM Album a " +
            "INNER JOIN tracks t ON a.id = t.album.id " +
            "WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :trackTitle, '%')) " +
            "ORDER BY a.releaseDate")
    public List<Album> findByTrackTitle(String trackTitle);

    @Query("SELECT a FROM Album a " +
           "WHERE " +
           "  (:artist IS NULL OR LOWER(a.artist) LIKE LOWER(CONCAT('%', :artist, '%'))) AND " +
           "  (:title IS NULL OR LOWER(a.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
           "ORDER BY a.releaseDate")
    public List<Album> findAlbums(String artist, String title);

}
