package com.albums.albumappbackend.controller;

import com.albums.albumappbackend.dto.ArtistDto;
import com.albums.albumappbackend.enums.Children;
import com.albums.albumappbackend.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ArtistController {

    @Autowired
    ArtistService artistService;

    @GetMapping("/artists")
    public List<ArtistDto> getArtists(@RequestParam(name="_embed", required=false) Children children) {
        return artistService.findAll(children);
    }

    @DeleteMapping("/artists/{id}")
    public void delete(@PathVariable("id") Long id) {
        artistService.delete(id);
    }

    @PostMapping("/artists")
    public ArtistDto create(@RequestBody @Valid ArtistDto artistDto) {
        return artistService.create(artistDto);
    }

    @PatchMapping("/artists/{id}")
    public ArtistDto patch(@PathVariable(name = "id") Long id,
                           @RequestBody Map<String, Object> changes) {
        return artistService.patch(id, changes);
    }

}
