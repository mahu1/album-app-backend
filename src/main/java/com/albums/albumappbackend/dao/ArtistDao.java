package com.albums.albumappbackend.dao;

import com.albums.albumappbackend.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistDao extends JpaRepository<Artist, Long> {
    @Query("SELECT a FROM Artist a " +
            "WHERE LOWER(a.title) = LOWER(:artistTitle) ")
    public List<Artist> findByArtistTitle(String artistTitle);

}
