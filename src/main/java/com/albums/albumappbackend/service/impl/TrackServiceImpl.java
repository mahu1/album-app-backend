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
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

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
        trackDao.deleteById(id);

        // Update track numbers
        Track track = existingTrack.get();
        Set<Track> tracks = track.getAlbum().getTracks();
        tracks.stream().filter(t -> t.getTrackNumber() > track.getTrackNumber()).forEach(t -> {
            t.setTrackNumber(t.getTrackNumber() - 1);
            update(t.getId(), t);
        });
    }

    @Override
    public Track create(Long albumId, Track track) {
        Optional<Album> existingAlbum = albumDao.findById(albumId);
        if (!existingAlbum.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album with id " + albumId + " does not exist");
        }
        if (isValueMissing(track)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
        }
        Album album = existingAlbum.get();
        track.setAlbum(album);
        Track savedTrack = trackDao.save(track);
        return savedTrack;
    }

    @Override
    public Track update(Long id, Track track) {
        Optional<Track> existingTrack = trackDao.findById(id);
        if (!existingTrack.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Track with id " + id + " does not exist");
        }
        if (isValueMissing(track)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
        }
        return trackDao.save(track);
    }

    private boolean isValueMissing(Track track) {
        return StringUtils.isEmpty(track.getTitle()) || StringUtils.isEmpty(track.getLength())
                || StringUtils.isEmpty(track.getTrackNumber());
    }

}
