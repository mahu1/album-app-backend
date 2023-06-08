package com.albums.albumappbackend.service.impl;

import com.albums.albumappbackend.dao.TrackDao;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.dao.AlbumDao;
import com.albums.albumappbackend.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumDao albumDao;
    @Autowired
    TrackDao trackDao;

    @Override
    public Optional<Album> findById(Long id) {
        if (!albumDao.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album with id " + id + " not found");
        }
        return albumDao.findById(id);
    }
    @Override
    public List<Album> findByTrackTitle(String trackTitle) {
        return albumDao.findByTrackTitle(trackTitle);
    }
    @Override
    public List<Album> findByArtist(String artist) {
        return albumDao.findByArtist(artist);
    }
    @Override
    public List<Album> findByTitle(String title) {
        return albumDao.findByTitle(title);
    }
    @Override
    public List<Album> findAll() {
        return albumDao.findAll(Sort.by(Sort.Direction.ASC, "releaseDate"));
    }
    @Override
    public void delete(Long id) {
        Optional<Album> existingAlbum = albumDao.findById(id);
        if (!existingAlbum.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album with id " + id + " does not exist");
        }

        // Delete album's tracks
        existingAlbum.get().getTracks().forEach(t -> {
            trackDao.deleteById(t.getId());
        });

        albumDao.deleteById(id);
    }
    @Override
    public Album create(Album album) {
        List<Album> result = findByArtistAndTitle(album.getArtist(), album.getTitle());
        if (!result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Album already found: " + album.getArtist() + " - " + album.getTitle());
        }
        return albumDao.save(album);
    }
    private List<Album> findByArtistAndTitle(String artist, String title) {
        return albumDao.findByArtistAndTitle(artist, title);
    }
    @Override
    public Album update(Long id, Album album) {
        Optional<Album> existingAlbum = albumDao.findById(id);
        if (!existingAlbum.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album with id " + id + " does not exist");
        }
        List<Album> result = findByArtistAndTitle(album.getArtist(), album.getTitle());
        if (!result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Album already found: " + album.getArtist() + " - " + album.getTitle());
        }

        Album foundAlbum = existingAlbum.get();
        foundAlbum.setTitle(album.getTitle());
        foundAlbum.setArtist(album.getArtist());
        foundAlbum.setCover(album.getCover());
        foundAlbum.setReleaseDate(album.getReleaseDate());
        return albumDao.save(foundAlbum);
    }

    @Override
    public Album patch(Long id, Map<String, Object> changes) {
        Optional<Album> existingAlbum = albumDao.findById(id);
        if (!existingAlbum.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album with id " + id + " does not exist");
        }
        Album album = existingAlbum.get();
        if (changes.get("artist") != null || changes.get("title") != null) {
            String artist = changes.get("artist") != null ? (String) changes.get("artist") : album.getArtist();
            String title = changes.get("title") != null ? (String) changes.get("title") : album.getTitle();
            List<Album> result = findByArtistAndTitle(artist, title);
            if (!result.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.FOUND, "Album already found: " + title + " - " + artist);
            }
        }

        changes.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Album.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, album, value);
        });

        return albumDao.save(album);
    }

}
