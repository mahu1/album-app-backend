package com.albums.albumappbackend.service.impl;

import com.albums.albumappbackend.dao.AlbumDao;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.entity.Track;
import com.albums.albumappbackend.dao.TrackDao;
import com.albums.albumappbackend.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class TrackServiceImpl implements TrackService {

    @Autowired
    TrackDao trackDao;
    @Autowired
    AlbumDao albumDao;

    @Override
    public void delete(Long id) {
        Optional<Track> existingTrack = trackDao.findById(id);
        if (!existingTrack.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Track with id " + id + " does not exist");
        }

        // Update track numbers
        Track track = existingTrack.get();
        Set<Track> tracks = track.getAlbum().getTracks();
        tracks.stream().filter(t -> t.getTrackNumber() > track.getTrackNumber()).forEach(t -> {
            t.setTrackNumber(t.getTrackNumber() - 1);
            Map<String, Object> changes = new HashMap();
            changes.put("trackNumber", t.getTrackNumber());
            patch(t.getId(), changes);
        });

        trackDao.deleteById(id);
    }

    @Override
    public Track create(Long albumId, Track track) {
        Optional<Album> existingAlbum = albumDao.findById(albumId);
        if (!existingAlbum.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album with id " + albumId + " does not exist");
        }

        Album album = existingAlbum.get();
        track.setAlbum(album);
        return trackDao.save(track);
    }

    @Override
    public Track update(Long id, Track track) {
        Optional<Track> existingTrack = trackDao.findById(id);
        if (!existingTrack.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Track with id " + id + " does not exist");
        }
        Track foundTrack = existingTrack.get();
        foundTrack.setTitle(track.getTitle());
        foundTrack.setLength(track.getLength());
        foundTrack.setTrackNumber(track.getTrackNumber());
        foundTrack.setAlbum(track.getAlbum());
        return trackDao.save(track);
    }

    @Override
    public Track patch(Long id, Map<String, Object> changes) {
        Optional<Track> existingTrack = trackDao.findById(id);
        if (!existingTrack.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Track with id " + id + " does not exist");
        }

        Track track = existingTrack.get();
        changes.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Track.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, track, value);
        });

        return trackDao.save(track);
    }

}
