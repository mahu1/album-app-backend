package com.albums.albumappbackend;

import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.dto.TrackDto;

import java.time.LocalDate;
import java.util.HashSet;

public class TestsData {

    public static AlbumDto getAlbum1() {
        AlbumDto albumDto1 = new AlbumDto(1L, "Iron & Wine", "Our Endless Numbered Days", "https://m.media-amazon.com/images/I/61Xzf+RI9sL._AC_.jpg", LocalDate.of(2004, 3, 23).toString(), new HashSet<>());
        TrackDto album1Track1 = new TrackDto(1L, "On Your Wings", "3:53", 1, albumDto1.id());
        albumDto1.tracks().add(album1Track1);
        TrackDto album1Track2 = new TrackDto(2L, "Naked as We Came", "2:33", 2, albumDto1.id());
        albumDto1.tracks().add(album1Track2);
        TrackDto album1Track3 = new TrackDto(3L, "Cinder and Smoke", "5:44", 3, albumDto1.id());
        albumDto1.tracks().add(album1Track3);
        return albumDto1;
    }

    public static AlbumDto getAlbum2() {
        AlbumDto albumDto2 = new AlbumDto(2L, "Abbey Road", "The Beatles", "https://m.media-amazon.com/images/I/A19M5TR8iYL._AC_SX679_.jpg", LocalDate.of(1969, 9, 26).toString(), new HashSet<>());
        TrackDto album2Track1 = new TrackDto(4L, "Come Together", "4:19", 1, albumDto2.id());
        albumDto2.tracks().add(album2Track1);
        TrackDto album2Track2 = new TrackDto(5L, "Something", "3:02", 2, albumDto2.id());
        albumDto2.tracks().add(album2Track2);
        return albumDto2;
    }
}