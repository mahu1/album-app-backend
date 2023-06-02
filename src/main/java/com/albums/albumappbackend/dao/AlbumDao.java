package com.albums.albumappbackend.dao;

import com.albums.albumappbackend.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumDao extends JpaRepository<Album, Long>  {
    @Query("SELECT A FROM Album A WHERE LOWER(A.artist) LIKE lower(concat('%', :artistToFind,'%')) ORDER BY A.releaseDate")
    public List<Album> findByArtist(@Param("artistToFind") String artist);

    @Query("SELECT A FROM Album A WHERE LOWER(A.title) LIKE lower(concat('%', :titleToFind,'%')) ORDER BY A.releaseDate")
    public List<Album> findByTitle(@Param("titleToFind") String title);

    @Query("SELECT A FROM Album A WHERE LOWER(A.artist) LIKE lower(concat('%', :artistToFind,'%')) AND LOWER(A.title) LIKE lower(concat('%', :titleToFind,'%')) ORDER BY A.releaseDate")
    public List<Album> findByArtistAndTitle(@Param("artistToFind") String artist, @Param("titleToFind") String title);

    @Query("SELECT A FROM Album A INNER JOIN tracks T ON A.id = T.album.id WHERE LOWER(T.title) LIKE lower(concat('%', :trackToFind,'%')) ORDER BY A.releaseDate")
    public List<Album> findByTrackTitle(@Param("trackToFind") String trackTitle);

}
