package com.albums.albumappbackend.service;

import com.albums.albumappbackend.dao.AlbumDao;
import com.albums.albumappbackend.dao.TrackDao;
import com.albums.albumappbackend.dto.TrackDto;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.entity.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class TrackService {

    @Autowired
    TrackDao trackDao;
    @Autowired
    AlbumDao albumDao;

    @Transactional
    public void delete(Long id) {
        Track track = trackDao.findById(id).orElseThrow();

        // Update track numbers
        Set<Track> tracks = track.getAlbum().getTracks();
        tracks.stream().filter(t -> t.getTrackNumber() > track.getTrackNumber()).forEach(t -> {
            Map<String, Object> changes = new HashMap();
            changes.put("trackNumber", t.getTrackNumber() - 1);
            patch(t.getId(), changes);
        });

        trackDao.deleteById(id);
    }

    @Transactional
    public TrackDto create(TrackDto trackDto) {
        Album album = albumDao.findById(trackDto.albumId()).orElseThrow();
        Track track = new Track(trackDto);
        track.setAlbum(album);
        Track savedTrack = trackDao.save(track);
        return new TrackDto(savedTrack);
    }

    @Transactional
    public TrackDto patch(Long id, Map<String, Object> changes) {
        Track track = trackDao.findById(id).orElseThrow();
        changes.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Track.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, track, value);
        });
        return new TrackDto(track);
    }

}
