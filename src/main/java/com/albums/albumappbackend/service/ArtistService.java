package com.albums.albumappbackend.service;

import com.albums.albumappbackend.dao.AlbumDao;
import com.albums.albumappbackend.dao.ArtistDao;
import com.albums.albumappbackend.dto.ArtistDto;
import com.albums.albumappbackend.entity.Album;
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
import java.util.List;
import java.util.Map;

@Service
public class ArtistService {

    @Autowired
    ArtistDao artistDao;

    @Autowired
    AlbumDao albumDao;

    @Transactional(readOnly = true)
    public List<ArtistDto> findAll(Children children) {
        List<Artist> artists = artistDao.findAll(Sort.by("title"));
        return buildResultDto(artists, children);
    }


    @Transactional
    public void delete(Long id) {
        Artist artist = artistDao.findById(id).orElseThrow();
        List<Album> albums = albumDao.findAlbums(artist.getTitle(), null);
        if (!albums.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Artist is used by " + albums.size() + " album(s)");
        }
        artistDao.deleteById(id);
    }

    @Transactional
    public ArtistDto create(ArtistDto artistDto) {
        List<Artist> artists = artistDao.findByTitle(artistDto.title());
        if (!artists.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Artist already found: " + artistDto.title());
        }
        Artist artist = new Artist(artistDto);
        Artist savedArtist = artistDao.save(artist);
        return new ArtistDto(savedArtist.getId(), savedArtist.getTitle());
    }

    @Transactional
    public ArtistDto patch(Long id, Map<String, Object> changes) {
        Artist artist = artistDao.findById(id).orElseThrow();
        changes.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Artist.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, artist, value);
        });
        return new ArtistDto(artist);
    }

    private List<ArtistDto> buildResultDto(List<Artist> artists, Children children) {
        if (children != null && children.equals(Children.ALBUMS)) {
            return artists.stream().map(a -> new ArtistDto(a)).toList();
        }
        return artists.stream().map(a -> new ArtistDto(a.getId(), a.getTitle())).toList();
    }

}

