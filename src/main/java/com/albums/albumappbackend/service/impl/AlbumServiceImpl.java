package com.albums.albumappbackend.service.impl;

import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.dao.AlbumDao;
import com.albums.albumappbackend.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumDao albumDao;

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
        albumDao.deleteById(id);
    }
    @Override
    public Album create(Album album) {
        List<Album> result = findByArtistAndTitle(album.getArtist(), album.getTitle());
        if (!result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Album already found: " + album.getArtist() + " - " + album.getTitle());
        } else if (isValueMissing(album)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
        }
        return albumDao.save(album);
    }
    private List<Album> findByArtistAndTitle(String artist, String title) {
        return albumDao.findByArtistAndTitle(artist, title);
    }
    @Override
    public Album update(Album album) {
        Optional<Album> existingAlbum = albumDao.findById(album.getId());
        if (!existingAlbum.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album with id " + album.getId() + " does not exist");
        } else if (isValueMissing(album)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
        }
        return albumDao.save(album);
    }

    private boolean isValueMissing(Album album) {
        return StringUtils.isEmpty(album.getTitle()) || StringUtils.isEmpty(album.getArtist())
                || StringUtils.isEmpty(album.getCover()) || album.getReleaseDate() == null;
    }

}
