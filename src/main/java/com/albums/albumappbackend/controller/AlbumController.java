package com.albums.albumappbackend.controller;

import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.service.impl.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class AlbumController {

    @Autowired
    AlbumServiceImpl albumService;

    final private String trackChildren = "tracks";

    @GetMapping("/album/{id}")
    public AlbumDto getById(@PathVariable("id") Long id, @RequestParam("_embed") Optional<String> children) {
        Optional<Album> album = albumService.findById(id);
        if (album.isPresent()) {
            Album albumFound = album.get();
            return buildResultDto(children, Arrays.asList(albumFound)).get(0);
        }
        return null;
    }

    @GetMapping("/albumsbyartist")
    public List<AlbumDto> getByArtist(@RequestParam("artist") String artist, @RequestParam("_embed") Optional<String> children) {
        List<Album> albums = albumService.findByArtist(artist);
        return buildResultDto(children, albums);
    }

    @GetMapping("/albumsbytitle")
    public List<AlbumDto> getByTitle(@RequestParam("title") String title, @RequestParam("_embed") Optional<String> children) {
        List<Album> albums = albumService.findByTitle(title);
        return buildResultDto(children, albums);
    }

    @GetMapping("/albumsbytracktitle")
    public List<AlbumDto> getByTrackTitle(@RequestParam("tracktitle") String trackTitle, @RequestParam("_embed") Optional<String> children) {
        List<Album> albums = albumService.findByTrackTitle(trackTitle);
        return buildResultDto(children, albums);
    }

    @GetMapping("/")
    public List<AlbumDto> getAlbums(@RequestParam("_embed") Optional<String> children) {
        List<Album> albums = albumService.findAll();
        return buildResultDto(children, albums);
    }

    @DeleteMapping("/album/{id}")
    public void delete(@PathVariable("id") Long id) {
        albumService.delete(id);
    }

    @PostMapping("/album")
    public AlbumDto create(@RequestBody AlbumDto albumDto) {
        Album album = new Album(albumDto);
        Album createdAlbum = albumService.create(album);
        return new AlbumDto(createdAlbum.getId(), createdAlbum.getTitle(), createdAlbum.getArtist(), createdAlbum.getCover(), createdAlbum.getReleaseDate());
    }

    @PutMapping("/album")
    public AlbumDto update(@RequestBody AlbumDto albumDto) {
        Album album = new Album(albumDto);
        Album updatedAlbum = albumService.update(album);
        return new AlbumDto(updatedAlbum.getId(), updatedAlbum.getTitle(), updatedAlbum.getArtist(), updatedAlbum.getCover(), updatedAlbum.getReleaseDate());
    }

    private List<AlbumDto> buildResultDto(Optional<String> children, List<Album> albums) {
        if (children.isPresent() && children.get().equals(trackChildren)) {
            return albums.stream().map(a -> new AlbumDto(a)).toList();
        }
        return albums.stream().map(a -> new AlbumDto(a.getId(), a.getTitle(), a.getArtist(), a.getCover(), a.getReleaseDate())).toList();
    }

}
