package com.albums.albumappbackend.dao;

import com.albums.albumappbackend.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumDao extends JpaRepository<Album, Long>  {
    @Query("SELECT a FROM Album a " +
            "INNER JOIN Artist ar ON a.artist.id = ar.id " +
            "WHERE " +
            "   LOWER(ar.title) = LOWER(:artist) AND " +
            "   LOWER(a.title) = LOWER(:title) " +
            "ORDER BY a.releaseDate")
    public List<Album> findByArtistAndTitle(String artist, String title);

    @Query("SELECT a FROM Album a " +
            "INNER JOIN tracks t ON a.id = t.album.id " +
            "WHERE " +
            "  LOWER(t.title) LIKE LOWER(CONCAT('%', :trackTitle, '%')) AND " +
            "  (:rating IS NULL OR a.rating >= :rating) " +
            "ORDER BY a.releaseDate, a.title")
    public List<Album> findByTrackTitle(String trackTitle, Integer rating);

    @Query("SELECT a FROM Album a " +
           "INNER JOIN Artist ar ON a.artist.id = ar.id " +
           "WHERE " +
           "  (:artist IS NULL OR LOWER(ar.title) LIKE LOWER(CONCAT('%', :artist, '%'))) AND " +
           "  (:title IS NULL OR LOWER(a.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
           "  (:rating IS NULL OR a.rating >= :rating) " +
           "ORDER BY a.releaseDate, a.title")
    public List<Album> findAlbums(String artist, String title, Integer rating);

}
