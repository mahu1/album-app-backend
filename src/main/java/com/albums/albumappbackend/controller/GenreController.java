package com.albums.albumappbackend.controller;

import com.albums.albumappbackend.dto.GenreDto;
import com.albums.albumappbackend.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping("/genres")
    public List<GenreDto> getGenres() {
        return genreService.findAll();
    }

}
