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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

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
        tracks.stream().filter(t ->  t.getDiscNumber() == track.getDiscNumber() && t.getTrackNumber() > track.getTrackNumber()).forEach(t -> {
            t.setTrackNumber(t.getTrackNumber() - 1);
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
            if (key == "trackNumber") {
                // Update duplicate disc and track number
                Album album = track.getAlbum();
                Stream<Track> duplicateTracks = album.getTracks().stream().filter(t -> t.getTrackNumber() == (Integer) value);
                if (changes.get("discNumber") != null) {
                    int discNumber = (Integer) changes.get("discNumber");
                    duplicateTracks = duplicateTracks.filter(t -> t.getDiscNumber() == discNumber);
                }
                Optional<Track> duplicateTrack = duplicateTracks.findFirst();
                if (duplicateTrack.isPresent()) {
                    duplicateTrack.get().setDiscNumber(track.getDiscNumber());
                    duplicateTrack.get().setTrackNumber(track.getTrackNumber());
                }
            }
            Field field = ReflectionUtils.findField(Track.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, track, value);
        });
        return new TrackDto(track);
    }

    @Transactional(readOnly = true)
    public List<String> findAllTrackTitles() {
        return trackDao.findAllTrackTitles();
    }

}
