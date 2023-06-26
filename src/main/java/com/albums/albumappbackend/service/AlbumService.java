package com.albums.albumappbackend.service;

import com.albums.albumappbackend.dao.ArtistDao;
import com.albums.albumappbackend.dao.TrackDao;
import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.dao.AlbumDao;
import com.albums.albumappbackend.entity.Artist;
import com.albums.albumappbackend.enums.Children;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;

@Service
public class AlbumService {

    @Autowired
    AlbumDao albumDao;
    @Autowired
    TrackDao trackDao;
    @Autowired
    ArtistDao artistDao;

    @Transactional(readOnly = true)
    public AlbumDto findById(Long id, Children children) {
        Album album = albumDao.findById(id).orElseThrow();
        return buildResultDto(Arrays.asList(album), children).get(0);
    }

    @Transactional(readOnly = true)
    public List<AlbumDto> findAlbums(String artist, String albumTitle, String trackTitle, Children children) {
        List<Album> albums;
        if (trackTitle != null) {
            albums = albumDao.findByTrackTitle(trackTitle);
        } else {
            albums = albumDao.findAlbums(artist, albumTitle);
        }
        return buildResultDto(albums, children);
    }

    @Transactional(readOnly = true)
    public List<AlbumDto> findAll(Children children) {
        List<Album> albums = albumDao.findAll(Sort.by(Sort.Direction.ASC, "releaseDate"));
        return buildResultDto(albums, children);
    }

    @Transactional
    public void delete(Long id) {
        Album album = albumDao.findById(id).orElseThrow();
        trackDao.deleteAll(album.getTracks());
        albumDao.deleteById(id);
    }

    @Transactional
    public AlbumDto create(AlbumDto albumDto) {
        List<Album> result = albumDao.findByArtistAndTitle(albumDto.artist().title(), albumDto.title());
        if (!result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Album already found: " + albumDto.artist() + " - " + albumDto.title());
        }
        Album album = new Album(albumDto);
        setExistingOrCreateNewArtist(album, albumDto);
        Album createdAlbum = albumDao.save(album);
        return new AlbumDto(createdAlbum.getId(), createdAlbum.getTitle(), createdAlbum.getArtist(), createdAlbum.getCover(), createdAlbum.getReleaseDate());
    }

    private void setExistingOrCreateNewArtist(Album album, AlbumDto albumDto) {
        List<Artist> artists = artistDao.findByTitle(albumDto.artist().title());
        if (!artists.isEmpty()) {
            album.setArtist(artists.get(0));
        } else {
            Artist artist = new Artist(albumDto.artist());
            Artist createdArtist = artistDao.save(artist);
            album.setArtist(createdArtist);
        }
    }

    @Transactional
    public AlbumDto put(Long id, AlbumDto albumDto) {
        List<Album> result = albumDao.findByArtistAndTitle(albumDto.artist().title(), albumDto.title());
        if (!result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Album already found: " + albumDto.artist() + " - " + albumDto.title());
        }
        Album album = albumDao.findById(id).orElseThrow();
        setExistingOrCreateNewArtist(album, albumDto);
        album.setCover(albumDto.cover());
        album.setReleaseDate(LocalDate.parse(albumDto.releaseDate()));
        return new AlbumDto(album.getId(), album.getTitle(), album.getArtist(), album.getCover(), album.getReleaseDate());
    }

    @Transactional
    public AlbumDto patch(Long id, Map<String, Object> changes) {
        Album album = albumDao.findById(id).orElseThrow();
        //if (changes.get("artist") != null || changes.get("title") != null) {
            //String artist = changes.get("artist") != null ? (String) changes.get("artist") : album.getArtist().getTitle();
            //String title = changes.get("title") != null ? (String) changes.get("title") : album.getTitle();
            //List<Album> result = albumDao.findByArtistAndTitle(artist, title);
            //if (!result.isEmpty()) {
                //throw new ResponseStatusException(HttpStatus.FOUND, "Album already found: " + title + " - " + artist);
            //}
        //}

        // Change release date type (String -> LocalData)
        if (changes.keySet().contains("releaseDate")) {
            String releaseDate = (String) changes.get("releaseDate");
            changes.replace("releaseDate", releaseDate, LocalDate.parse(releaseDate));
        }

        changes.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Album.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, album, value);
        });

        return new AlbumDto(album.getId(), album.getTitle(), album.getArtist(), album.getTitle(), album.getReleaseDate());
    }

    private List<AlbumDto> buildResultDto(List<Album> albums, Children children) {
        if (children != null && children.name().equals("TRACKS")) {
            return albums.stream().map(a -> new AlbumDto(a)).toList();
        }
        return albums.stream().map(a -> new AlbumDto(a.getId(), a.getTitle(), a.getArtist(), a.getCover(), a.getReleaseDate())).toList();
    }

}
