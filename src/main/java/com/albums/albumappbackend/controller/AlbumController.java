package com.albums.albumappbackend.controller;

import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.service.impl.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
public class AlbumController {

    @Autowired
    AlbumServiceImpl albumService;

    final private String TRACK_CHILDREN = "tracks";

    @GetMapping("/albums/{id}")
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

    @DeleteMapping("/albums/{id}")
    public void delete(@PathVariable("id") Long id) {
        albumService.delete(id);
    }

    @PostMapping("/albums")
    public AlbumDto create(@RequestBody AlbumDto albumDto) {
        Album album = new Album(albumDto);
        Album createdAlbum = albumService.create(album);
        return new AlbumDto(createdAlbum.getId(), createdAlbum.getTitle(), createdAlbum.getArtist(), createdAlbum.getCover(), createdAlbum.getReleaseDate());
    }

    @PutMapping("/albums")
    public AlbumDto update(@PathVariable("id") Long id, @RequestBody AlbumDto albumDto) {
        Album album = new Album(albumDto);
        Album updatedAlbum = albumService.update(id, album);
        return new AlbumDto(updatedAlbum.getId(), updatedAlbum.getTitle(), updatedAlbum.getArtist(), updatedAlbum.getCover(), updatedAlbum.getReleaseDate());
    }

    @PatchMapping("/albums/{id}")
    public AlbumDto patch(@PathVariable(name = "id") Long id, @RequestBody Map<String, Object> changes) {
        if (changes.keySet().contains("releaseDate")) {
            String releaseDate = (String) changes.get("releaseDate");
            changes.replace("releaseDate", releaseDate, LocalDate.parse(releaseDate));
        }
        Album updatedAlbum = albumService.patch(id, changes);
        return new AlbumDto(updatedAlbum.getId(), updatedAlbum.getTitle(), updatedAlbum.getArtist(), updatedAlbum.getCover(), updatedAlbum.getReleaseDate());
    }

    private List<AlbumDto> buildResultDto(Optional<String> children, List<Album> albums) {
        if (children.isPresent() && children.get().equals(TRACK_CHILDREN)) {
            return albums.stream().map(a -> new AlbumDto(a)).toList();
        }
        return albums.stream().map(a -> new AlbumDto(a.getId(), a.getTitle(), a.getArtist(), a.getCover(), a.getReleaseDate())).toList();
    }

}
