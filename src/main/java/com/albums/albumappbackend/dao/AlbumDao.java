package com.albums.albumappbackend.dao;

import com.albums.albumappbackend.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumDao extends JpaRepository<Album, Long>  {
    @Query("SELECT a FROM Album a " +
           "INNER JOIN tracks t ON a.id = t.album.id " +
           "WHERE " +
           "  LOWER(t.title) LIKE LOWER(CONCAT('%', :trackTitle, '%')) AND " +
           "  (:rating IS NULL OR a.rating >= :rating) AND " +
           "  (COALESCE(:genreIds) IS NULL OR EXISTS (SELECT g FROM Genre g WHERE g.id IN (:genreIds) AND g MEMBER OF a.genres)) " +
           "ORDER BY a.releaseDate, a.title")
    public List<Album> findByTrackTitle(String trackTitle, Integer rating, List<Long> genreIds);

    @Query("SELECT a FROM Album a " +
           "INNER JOIN Artist ar ON a.artist.id = ar.id " +
           "WHERE " +
           "  (:artistTitle IS NULL OR LOWER(ar.title) LIKE LOWER(CONCAT('%', :artistTitle, '%'))) AND " +
           "  (:albumTitle IS NULL OR LOWER(a.title) LIKE LOWER(CONCAT('%', :albumTitle, '%'))) AND " +
           "  (:rating IS NULL OR a.rating >= :rating) AND " +
           " (COALESCE(:genreIds) IS NULL OR EXISTS (SELECT g FROM Genre g WHERE g.id IN (:genreIds) AND g MEMBER OF a.genres)) " +
           "ORDER BY a.releaseDate, a.title")
    public List<Album> findBy(String artistTitle, String albumTitle, Integer rating, List<Long> genreIds);

    @Query("SELECT a FROM Album a " +
           "INNER JOIN Artist ar ON a.artist.id = ar.id " +
           "WHERE " +
           "  LOWER(a.title) = LOWER(:albumTitle) AND LOWER(ar.title) = LOWER(:artistTitle) " +
           "ORDER BY a.title, ar.title")
    public List<Album> findByAlbumAndArtistTitle(String albumTitle, String artistTitle);

}
