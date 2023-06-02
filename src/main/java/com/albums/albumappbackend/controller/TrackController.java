package com.albums.albumappbackend.controller;

import com.albums.albumappbackend.dto.TrackDto;
import com.albums.albumappbackend.entity.Track;
import com.albums.albumappbackend.service.impl.TrackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TrackController {

    @Autowired
    TrackServiceImpl trackService;

    @DeleteMapping("/track/{id}")
    public void delete(@PathVariable("id") Long id) {
        trackService.delete(id);
    }

    @PostMapping("/track")
    public TrackDto create(@RequestParam(value = "albumId") Long albumId, @RequestBody TrackDto trackDto) {
        Track track = new Track(trackDto);
        Track createdTrack = trackService.create(albumId, track);
        return new TrackDto(createdTrack);
    }

    @PutMapping("/track")
    public TrackDto update(@PathVariable("id") Long id, @RequestBody TrackDto trackDto) {
        Track track = new Track(trackDto);
        Track updatedTrack = trackService.update(id, track);
        return new TrackDto(updatedTrack);
    }

}
