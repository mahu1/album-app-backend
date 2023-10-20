package com.albums.albumappbackend.controller;

import com.albums.albumappbackend.dto.TrackDto;
import com.albums.albumappbackend.service.TrackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TrackController {

    @Autowired
    TrackService trackService;

    @DeleteMapping("/tracks/{id}")
    public void delete(@PathVariable("id") Long id) {
        trackService.delete(id);
    }

    @PostMapping("/tracks")
    public TrackDto create(@RequestBody @Valid TrackDto trackDto) {
        return trackService.create(trackDto);
    }

    @PatchMapping("/tracks/{id}")
    public TrackDto patch(@PathVariable(name = "id") Long id,
                          @RequestBody Map<String, Object> changes) {
        return trackService.patch(id, changes);
    }

    @GetMapping("/tracks")
    public List<String> getAllTrackTitles() {
        return trackService.findAllTrackTitles();
    }

}
