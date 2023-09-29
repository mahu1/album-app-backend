package com.albums.albumappbackend.service;

import com.albums.albumappbackend.dao.AlbumDao;
import com.albums.albumappbackend.dao.ArtistDao;
import com.albums.albumappbackend.dao.GenreDao;
import com.albums.albumappbackend.dao.TrackDao;
import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.dto.ArtistDto;
import com.albums.albumappbackend.dto.GenreDto;
import com.albums.albumappbackend.dto.TrackDto;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.entity.Artist;
import com.albums.albumappbackend.entity.Genre;
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
import java.util.stream.Collectors;

@Service
public class AlbumService {

    @Autowired
    AlbumDao albumDao;
    @Autowired
    TrackDao trackDao;
    @Autowired
    ArtistDao artistDao;
    @Autowired
    GenreDao genreDao;

    @Transactional(readOnly = true)
    public AlbumDto findById(Long id, List<Children> children) {
        Album album = albumDao.findById(id).orElseThrow();
        return buildResultDto(Arrays.asList(album), children).get(0);
    }

    @Transactional(readOnly = true)
    public List<AlbumDto> findAlbums(String artist, String albumTitle, String trackTitle, Integer rating, List<String> genres, List<Children> children) {
        List<Album> albums;
        if (trackTitle != null) {
            albums = albumDao.findByTrackTitle(trackTitle, rating, genres);
        } else {
            albums = albumDao.findAlbums(artist, albumTitle, rating, genres);
        }
        return buildResultDto(albums, children);
    }

    @Transactional(readOnly = true)
    public List<AlbumDto> findAll(List<Children> children) {
        List<Album> albums = albumDao.findAll(Sort.by(Sort.Direction.ASC, "releaseDate", "title"));
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
        List<Artist> artists = artistDao.findByTitle(albumDto.artist().title());
        if (artists.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist cannot found: " + albumDto.artist().title());
        }
        Set<Genre> genres = new HashSet<>();
        for (GenreDto genreDto : albumDto.genres()) {
            List<Genre> genreResult = genreDao.findByTitle(genreDto.title());
            if (genreResult.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre cannot found: " + genreDto.title());
            }
            genres.add(genreResult.get(0));
        }
        Album album = new Album(albumDto);
        album.setArtist(artists.get(0));
        album.setGenres(genres);
        Album createdAlbum = albumDao.save(album);
        return new AlbumDto(createdAlbum.getId(), createdAlbum.getTitle(), new ArtistDto(createdAlbum.getArtist().getId(), createdAlbum.getArtist().getTitle(), null), createdAlbum.getCover(), createdAlbum.getReleaseDate().toString(), createdAlbum.getRating(), null, album.getGenres().stream().map(g -> new GenreDto(g.getId(), g.getTitle(), null)).collect(Collectors.toSet()));
    }

    @Transactional
    public AlbumDto put(Long id, AlbumDto albumDto) {
        Album album = albumDao.findById(id).orElseThrow();
        if (!album.getArtist().getTitle().equals(albumDto.artist().title()) || !album.getTitle().equals(albumDto.title()))
        {
            List<Album> albums = albumDao.findByArtistAndTitle(albumDto.artist().title(), albumDto.title());
            if (!albums.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.FOUND, "Album already found: " + albumDto.artist() + " - " + albumDto.title());
            }
        }
        List<Artist> artists = artistDao.findByTitle(albumDto.artist().title());
        if (artists.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist cannot found: " + albumDto.artist().title());
        }
        Set<Genre> genres = new HashSet<>();
        for (GenreDto genreDto : albumDto.genres()) {
            List<Genre> genreResult = genreDao.findByTitle(genreDto.title());
            if (genreResult.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre cannot found: " + genreDto.title());
            }
            genres.add(genreResult.get(0));
        }
        album.setTitle(albumDto.title());
        album.setCover(albumDto.cover());
        album.setReleaseDate(LocalDate.parse(albumDto.releaseDate()));
        album.setRating(albumDto.rating());
        album.setArtist(artists.get(0));
        album.setGenres(genres);
        return new AlbumDto(album.getId(), album.getTitle(), new ArtistDto(album.getArtist()), album.getCover(), album.getReleaseDate().toString(), album.getRating(), null, album.getGenres().stream().map(g -> new GenreDto(g.getId(), g.getTitle(), null)).collect(Collectors.toSet()));
    }

    @Transactional
    public AlbumDto patch(Long id, Map<String, Object> changes) {
        Album album = albumDao.findById(id).orElseThrow();
        if (changes.get("title") != null) {
            String title = (String) changes.get("title");
            List<Album> result = albumDao.findByArtistAndTitle(album.getArtist().getTitle(), title);
            if (!result.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.FOUND, "Album already found: " + title + " - " + album.getArtist().getTitle());
            }
        }

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
        return new AlbumDto(album.getId(), album.getTitle(), new ArtistDto(album.getArtist()), album.getCover(), album.getReleaseDate().toString(), album.getRating(), null, null);
    }

    private List<AlbumDto> buildResultDto(List<Album> albums, List<Children> children) {
        if (children != null && children.contains(Children.TRACKS) && children.contains(Children.GENRES)) {
            return albums.stream().map(a -> new AlbumDto(a)).toList();
        } else if (children != null && children.equals(Children.TRACKS)) {
            return albums.stream().map(a -> new AlbumDto(a.getId(), a.getTitle(), new ArtistDto(a.getArtist().getId(), a.getArtist().getTitle(), null), a.getCover(), a.getReleaseDate().toString(), a.getRating(), a.getTracks().stream().map(t -> new TrackDto(t)).collect(Collectors.toSet()), null)).toList();
        } else if (children != null && children.equals(Children.GENRES)) {
            return albums.stream().map(a -> new AlbumDto(a.getId(), a.getTitle(), new ArtistDto(a.getArtist().getId(), a.getArtist().getTitle(), null), a.getCover(), a.getReleaseDate().toString(), a.getRating(), null, a.getGenres().stream().map(g -> new GenreDto(g)).collect(Collectors.toSet()))).toList();
        }
        return albums.stream().map(a -> new AlbumDto(a.getId(), a.getTitle(), new ArtistDto(a.getArtist().getId(), a.getArtist().getTitle(), null), a.getCover(), a.getReleaseDate().toString(), a.getRating(), null, null)).toList();
    }

}
