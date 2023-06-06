package com.albums.albumappbackend.service;

import com.albums.albumappbackend.entity.Track;

import java.util.Map;


public interface TrackService {

    void delete(Long id);

    Track create(Long albumId, Track track);

    Track update(Long id, Track track);

    Track patch(Long id, Map<String, Object> changes);
}
