package com.albums.albumappbackend.controller;

import com.albums.albumappbackend.dto.GenreDto;
import com.albums.albumappbackend.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping("/genres")
    public List<GenreDto> getGenres() {
        return genreService.findAll();
    }

    @DeleteMapping("/genres/{id}")
    public void delete(@PathVariable("id") Long id) {
        genreService.delete(id);
    }

    @PostMapping("/genres")
    public GenreDto create(@RequestBody @Valid GenreDto genreDto) {
        return genreService.create(genreDto);
    }

    @PatchMapping("/genres/{id}")
    public GenreDto patch(@PathVariable(name = "id") Long id,
                           @RequestBody Map<String, Object> changes) {
        return genreService.patch(id, changes);
    }


}
