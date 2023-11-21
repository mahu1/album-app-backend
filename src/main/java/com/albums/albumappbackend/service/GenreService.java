package com.albums.albumappbackend.service;

import com.albums.albumappbackend.dao.AlbumDao;
import com.albums.albumappbackend.dao.GenreDao;
import com.albums.albumappbackend.dto.GenreDto;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class GenreService {

    @Autowired
    GenreDao genreDao;

    @Autowired
    AlbumDao albumdao;

    @Transactional(readOnly = true)
    public List<GenreDto> findAll() {
        List<Genre> genres = genreDao.findAll(Sort.by("title"));
        return genres.stream().map(g -> new GenreDto(g)).toList();
    }

    @Transactional
    public void delete(Long id) {
        Genre genre = genreDao.findById(id).orElseThrow();
        List<Album> albums = albumdao.findByGenreId(id);
        albums.stream().forEach(a -> a.getGenres().remove(genre));
        genreDao.deleteById(id);
    }

    @Transactional
    public GenreDto create(GenreDto genreDto) {
        List<Genre> genres = genreDao.findByGenreTitle(genreDto.title());
        if (!genres.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Genre already found: " + genreDto.title());
        }
        Genre genre = new Genre(genreDto);
        Genre savedGenre = genreDao.save(genre);
        return new GenreDto(savedGenre.getId(), savedGenre.getTitle(), null);
    }

    @Transactional
    public GenreDto patch(Long id, Map<String, Object> changes) {
        Genre genre = genreDao.findById(id).orElseThrow();
        changes.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Genre.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, genre, value);
        });
        return new GenreDto(genre);
    }

}
