package com.albums.albumappbackend.controller;

import com.albums.albumappbackend.dto.TrackDto;
import com.albums.albumappbackend.entity.Track;
import com.albums.albumappbackend.service.impl.AlbumServiceImpl;
import com.albums.albumappbackend.service.impl.TrackServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class TrackController {

    @Autowired
    TrackServiceImpl trackService;
    @Autowired
    AlbumServiceImpl albumService;

    @DeleteMapping("/tracks/{id}")
    public void delete(@PathVariable("id") Long id) {
        trackService.delete(id);
    }

    @PostMapping("/tracks")
    public TrackDto create(@RequestParam(value = "albumId") Long albumId, @RequestBody @Valid TrackDto trackDto) {
        Track track = new Track(trackDto);
        Track createdTrack = trackService.create(albumId, track);
        return new TrackDto(createdTrack);
    }

    @PutMapping("/tracks/{id}")
    public TrackDto update(@PathVariable("id") Long id, @RequestBody @Valid TrackDto trackDto) {
        Track track = new Track(trackDto);
        track.setAlbum(albumService.findById(trackDto.getAlbumId()).get());
        Track updatedTrack = trackService.update(id, track);
        return new TrackDto(updatedTrack);
    }

    @PatchMapping("/tracks/{id}")
    public TrackDto patch(@PathVariable(name = "id") Long id, @RequestBody Map<String, Object> changes) {
        Track updatedTrack = trackService.patch(id, changes);
        return new TrackDto(updatedTrack);
    }

}
