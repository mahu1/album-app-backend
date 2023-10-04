package com.albums.albumappbackend.dao;

import com.albums.albumappbackend.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreDao extends JpaRepository<Genre, Long> {

}
