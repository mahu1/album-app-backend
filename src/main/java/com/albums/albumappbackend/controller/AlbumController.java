package com.albums.albumappbackend.controller;

import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.dto.AlbumPlainDto;
import com.albums.albumappbackend.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @GetMapping("/albums/{id}")
    public AlbumDto getById(@PathVariable("id") Long id) {
        return albumService.findById(id);
    }

    @GetMapping("/albums")
    public List<AlbumPlainDto> getAlbums(@RequestParam(name="artist", required=false) String artistTitle,
                                         @RequestParam(name="album", required=false) String albumTitle,
                                         @RequestParam(name="track", required=false) String trackTitle,
                                         @RequestParam(name="rating", required=false) Integer rating,
                                         @RequestParam(name="genres", required=false) List<Long> genreIds) {
        return albumService.findAlbums(artistTitle, albumTitle, trackTitle, rating, genreIds);
    }

    @GetMapping("/")
    public List<AlbumPlainDto> getAlbums() {
        return albumService.findAll();
    }

    @DeleteMapping("/albums/{id}")
    public void delete(@PathVariable("id") Long id) {
        albumService.delete(id);
    }

    @PostMapping("/albums")
    public AlbumDto create(@RequestBody @Valid AlbumDto albumDto) {
            return albumService.create(albumDto);
    }

    @PatchMapping("/albums/{id}")
    public AlbumDto patch(@PathVariable(name = "id") Long id,
                          @RequestBody @Valid AlbumDto albumDto) {
        return albumService.patch(id, albumDto);
    }

}
