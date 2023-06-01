package com.albums.albumappbackend.service;

import com.albums.albumappbackend.entity.Track;


public interface TrackService {

    void delete(Long id);

    Track create(Track track);

    Track update(Track track);
}
