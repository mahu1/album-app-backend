package com.albums.albumappbackend.controller;

import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.service.impl.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class AlbumController {

    private AlbumServiceImpl albumService;

    @Autowired
    public AlbumController(AlbumServiceImpl albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/album")
    public Optional<Album> findById(@RequestParam("id") Long id) {
        return albumService.findById(id);
    }

    @GetMapping("/albumandtracks")
    public Album findByIdWithTracks(@RequestParam("id") Long id) {
        return albumService.findByIdWithTracks(id);
    }

    @GetMapping("/albums")
    public List<Album> findByIds(@RequestParam("ids") Set<Long> ids) {
        return albumService.findByIds(ids);
    }

    @GetMapping("/albumbyartist")
    public List<Album> findByArtist(@RequestParam("artist") String artist) {
        return albumService.findByArtist(artist);
    }

    @GetMapping("/albumbytitle")
    public List<Album> findByTitle(@RequestParam("title") String title) {
        return albumService.findByTitle(title);
    }

    @GetMapping("/")
    public List<Album> findAll() {
        return albumService.findAll();
    }

    @DeleteMapping("/album")
    public void delete(@RequestParam("id") Long id) {
        albumService.delete(id);
    }

    @PostMapping("/album")
    public Album create(@RequestBody Album album) {
        List<Album> result = albumService.findByArtistAndTitle(album.getArtist(), album.getTitle());
        if (!result.isEmpty() || album.isValueMissing()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
        }
        return albumService.create(album);
    }

    @PutMapping("/album")
    public void update(@RequestBody Album album) {
        albumService.update(album);
    }

}
