package com.albums.albumappbackend.controller;

import com.albums.albumappbackend.dto.GenreDto;
import com.albums.albumappbackend.enums.Children;
import com.albums.albumappbackend.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    GenreService genreService;


    @GetMapping("/genres/{id}")
    public GenreDto getById(@PathVariable("id") Long id,
                            @RequestParam(name="_embed", required=false) Children children) {
        return genreService.findById(id, children);
    }

    @GetMapping("/genres")
    public List<GenreDto> getGenres(@RequestParam(name="_embed", required=false) Children children) {
        return genreService.findAll(children);
    }

}
