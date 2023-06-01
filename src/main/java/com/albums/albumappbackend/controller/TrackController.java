package com.albums.albumappbackend.controller;

import com.albums.albumappbackend.entity.Track;
import com.albums.albumappbackend.service.impl.TrackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TrackController {

    private TrackServiceImpl trackService;

    @Autowired
    public TrackController(TrackServiceImpl trackService) {
        this.trackService = trackService;
    }

    @DeleteMapping("/track")
    public void delete(@RequestParam("id") Long id) {
        trackService.delete(id);
    }

    @PostMapping("/track")
    public Track create(@RequestBody Track track) {
        if (track.isValueMissing()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
        }
        return trackService.create(track);
    }

    @PutMapping("/track")
    public void update(@RequestBody Track track) {
        trackService.update(track);
    }

}
