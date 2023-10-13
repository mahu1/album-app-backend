package com.albums.albumappbackend.service;

import com.albums.albumappbackend.dao.AlbumDao;
import com.albums.albumappbackend.dao.ArtistDao;
import com.albums.albumappbackend.dao.GenreDao;
import com.albums.albumappbackend.dao.TrackDao;
import com.albums.albumappbackend.dto.*;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.entity.Artist;
import com.albums.albumappbackend.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    public AlbumDto findById(Long id) {
        Album album = albumDao.findById(id).orElseThrow();
        return new AlbumDto(album);
    }

    @Transactional(readOnly = true)
    public List<AlbumPlainDto> findAlbums(String artistTitle, String albumTitle, String trackTitle, Double rating, List<Long> genreIds, LocalDate releaseDateStart, LocalDate releaseDateEnd) {
        List<Album> albums;
        if (trackTitle != null) {
            albums = albumDao.findByTrackTitle(trackTitle, rating, genreIds, releaseDateStart, releaseDateEnd);
        } else if (artistTitle != null) {
            albums = albumDao.findByArtistTitle(artistTitle, rating, genreIds, releaseDateStart, releaseDateEnd);
        } else {
            albums = albumDao.findByAlbumTitle(albumTitle, rating, genreIds, releaseDateStart, releaseDateEnd);
        }
        return albums.stream().map(a -> new AlbumPlainDto(a)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AlbumPlainDto> findAll() {
        List<Album> albums = albumDao.findAll(Sort.by(Sort.Direction.ASC, "releaseDate", "title"));
        return albums.stream().map(a -> new AlbumPlainDto(a)).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Album album = albumDao.findById(id).orElseThrow();
        trackDao.deleteAll(album.getTracks());
        albumDao.deleteById(id);
    }

    @Transactional
    public AlbumDto create(AlbumDto albumDto) {
        validateAlbumData(albumDto);
        Album album = new Album(albumDto);
        List<Artist> artists = artistDao.findByArtistTitle(albumDto.artist().title());
        album.setArtist(artists.get(0));
        List<Genre> genres = genreDao.findAllById(albumDto.genres().stream().map(g -> g.id()).collect(Collectors.toSet()));
        album.setGenres(new HashSet<>(genres));
        Album createdAlbum = albumDao.save(album);
        return new AlbumDto(createdAlbum.getId(), createdAlbum.getTitle(), new ArtistDto(createdAlbum.getArtist().getId(), createdAlbum.getArtist().getTitle(), null), createdAlbum.getCover(), createdAlbum.getReleaseDate(), createdAlbum.getRating(), null, album.getGenres().stream().map(g -> new GenreDto(g.getId(), g.getTitle(), null)).collect(Collectors.toSet()));
    }

    @Transactional
    public AlbumDto put(Long id, AlbumDto albumDto) {
        validateAlbumData(albumDto);
        Album album = albumDao.findById(id).orElseThrow();
        album.setTitle(albumDto.title());
        album.setCover(albumDto.cover());
        album.setReleaseDate(albumDto.releaseDate());
        album.setRating(albumDto.rating());
        List<Artist> artists = artistDao.findByArtistTitle(albumDto.artist().title());
        album.setArtist(artists.get(0));
        List<Genre> genres = genreDao.findAllById(albumDto.genres().stream().map(g -> g.id()).collect(Collectors.toSet()));
        album.setGenres(new HashSet<>(genres));
        return new AlbumDto(album.getId(), album.getTitle(), new ArtistDto(album.getArtist()), album.getCover(), album.getReleaseDate(), album.getRating(), null, null);
    }

    private void validateAlbumData(AlbumDto albumDto) {
        List<Album> foundAlbums = albumDao.findByAlbumAndArtistTitle(albumDto.title(), albumDto.artist().title());
        if (foundAlbums.stream().anyMatch(a -> a.getId() != albumDto.id())) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Album already found: " + albumDto.artist().title() + " - " + albumDto.title());
        }
        List<Artist> foundArtists = artistDao.findByArtistTitle(albumDto.artist().title());
        if (foundArtists.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist cannot found: " + albumDto.artist().title());
        }
        List<Genre> foundGenres = genreDao.findAllById(albumDto.genres().stream().map(g -> g.id()).collect(Collectors.toSet()));
        Set<Long> genreIds = foundGenres.stream().map(g -> g.getId()).collect(Collectors.toSet());
        Set<GenreDto> notFoundGenres = albumDto.genres().stream().filter(g -> !genreIds.contains(g.id())).collect(Collectors.toSet());
        if (!notFoundGenres.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre(s) cannot found: " + notFoundGenres.stream().map(g -> g.title()).collect(Collectors.joining(", ")));
        }
    }

}
