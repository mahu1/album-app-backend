package com.albums.albumappbackend.controller;

import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.enums.Children;
import com.albums.albumappbackend.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @GetMapping("/albums/{id}")
    public AlbumDto getById(@PathVariable("id") Long id,
                            @RequestParam(name="_embed", required=false) Children children) {
        return albumService.findById(id, children);
    }

    @GetMapping("/albums")
    public List<AlbumDto> getAlbums(@RequestParam(name="artist", required=false) String artist,
                                    @RequestParam(name="albumtitle", required=false) String albumTitle,
                                    @RequestParam(name="tracktitle", required=false) String trackTitle,
                                    @RequestParam(name="rating", required=false) Integer rating,
                                    @RequestParam(name="_embed", required=false) Children children) {
        return albumService.findAlbums(artist, albumTitle, trackTitle, rating, children);
    }

    @GetMapping("/")
    public List<AlbumDto> getAlbums(@RequestParam(name="_embed", required=false) Children children) {
        return albumService.findAll(children);
    }

    @DeleteMapping("/albums/{id}")
    public void delete(@PathVariable("id") Long id) {
        albumService.delete(id);
    }

    @PostMapping("/albums")
    public AlbumDto create(@RequestBody @Valid AlbumDto albumDto) {
            return albumService.create(albumDto);
    }

    @PutMapping("/albums/{id}")
    public AlbumDto put(@PathVariable("id") Long id,
                        @RequestBody @Valid AlbumDto albumDto) {
        return albumService.put(id, albumDto);
    }

    @PatchMapping("/albums/{id}")
    public AlbumDto patch(@PathVariable(name = "id") Long id,
                          @RequestBody Map<String, Object> changes) {
        return albumService.patch(id, changes);
    }

}
