package com.albums.albumappbackend.service;

import com.albums.albumappbackend.entity.Album;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AlbumService {

    Optional<Album> findById(Long id);

    Album findByIdWithTracks(Long id);

    List<Album> findByIds(Set<Long> ids);

    List<Album> findByArtist(String artist);

    List<Album> findByTitle(String title);

    List<Album> findByArtistAndTitle(String artist, String title);

    List<Album> findAll();

    void delete(Long id);

    Album create(Album album);

    Album update(Album album);
}
