package com.albums.albumappbackend.dao;

import com.albums.albumappbackend.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackDao extends JpaRepository<Track, Long> {

    @Query("SELECT DISTINCT t.title FROM Track t ORDER BY t.title")
    public List<String> findAllTrackTitles();

}
