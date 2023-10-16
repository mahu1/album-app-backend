package com.albums.albumappbackend.service;

import com.albums.albumappbackend.dao.GenreDao;
import com.albums.albumappbackend.dto.GenreDto;
import com.albums.albumappbackend.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreDao genreDao;

    @Transactional(readOnly = true)
    public List<GenreDto> findAll() {
        List<Genre> genres = genreDao.findAll(Sort.by("title"));
        return genres.stream().map(g -> new GenreDto(g)).toList();
    }

}
