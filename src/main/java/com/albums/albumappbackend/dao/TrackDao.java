package com.albums.albumappbackend.dao;

import com.albums.albumappbackend.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackDao extends JpaRepository<Track, Long> {

}
