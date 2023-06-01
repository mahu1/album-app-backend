package com.albums.albumappbackend.service.impl;

import com.albums.albumappbackend.entity.Track;
import com.albums.albumappbackend.repository.TrackRepository;
import com.albums.albumappbackend.service.TrackService;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;

    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void delete(Long id) {
        trackRepository.deleteById(id);
    }

    @Override
    public Track create(Track track) {
        return trackRepository.save(track);
    }

    @Override
    public Track update(Track track) {
        return trackRepository.save(track);
    }

}
