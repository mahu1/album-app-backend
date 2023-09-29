package com.albums.albumappbackend.service;

import com.albums.albumappbackend.dao.GenreDao;
import com.albums.albumappbackend.dto.GenreDto;
import com.albums.albumappbackend.entity.Genre;
import com.albums.albumappbackend.enums.Children;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreDao genreDao;

    @Transactional(readOnly = true)
    public GenreDto findById(Long id, Children children) {
        Genre genre = genreDao.findById(id).orElseThrow();
        return buildResultDto(Arrays.asList(genre), children).get(0);
    }

    @Transactional(readOnly = true)
    public List<GenreDto> findAll(Children children) {
        List<Genre> genres = genreDao.findAll(Sort.by("title"));
        return buildResultDto(genres, children);
    }

    private List<GenreDto> buildResultDto(List<Genre> genres, Children children) {
        if (children != null && children.equals(Children.ALBUMS)) {
            return genres.stream().map(g -> new GenreDto(g)).toList();
        }
        return genres.stream().map(g -> new GenreDto(g.getId(), g.getTitle(), null)).toList();
    }

}
