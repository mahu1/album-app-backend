package com.albums.albumappbackend.dao;

import com.albums.albumappbackend.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreDao extends JpaRepository<Genre, Long> {
    @Query("SELECT g FROM Genre g " +
            "WHERE LOWER(g.title) = LOWER(:genreTitle)")
    public List<Genre> findByGenreTitle(String genreTitle);

    @Query("SELECT g FROM Genre g " +
            "WHERE LOWER(g.title) IN :genreTitles")
    public List<Genre> findByGenreTitles(List<String> genreTitles);

}
