package com.albums.albumappbackend.service;

import com.albums.albumappbackend.entity.Track;


public interface TrackService {

    void delete(Long id);

    Track create(Long albumId, Track track);

    Track update(Track track);

}
