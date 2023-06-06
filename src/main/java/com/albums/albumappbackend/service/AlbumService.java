package com.albums.albumappbackend.service;

import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.entity.Album;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AlbumService {

    Optional<Album> findById(Long id);

    List<Album> findByArtist(String artist);

    List<Album> findByTitle(String title);

    List<Album> findByTrackTitle(String trackTitle);

    List<Album> findAll();

    void delete(Long id);

    Album create(Album album);

    Album update(Long id, Album album);

    Album patch(Long id, Map<String, Object> changes);
}
