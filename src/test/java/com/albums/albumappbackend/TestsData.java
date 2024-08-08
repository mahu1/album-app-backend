package com.albums.albumappbackend;

import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.dto.ArtistDto;
import com.albums.albumappbackend.dto.GenreDto;
import com.albums.albumappbackend.dto.TrackDto;

import java.time.LocalDate;
import java.util.HashSet;

public class TestsData {

    public static AlbumDto getAlbum1() {
        AlbumDto albumDto1 = new AlbumDto(1L, "Our Endless Numbered Days", new ArtistDto(1L, "Iron & Wine", new HashSet<>()), "https://m.media-amazon.com/images/I/61Xzf+RI9sL._AC_.jpg", LocalDate.of(2004, 3, 23), null, new HashSet<>(), new HashSet<>());
        TrackDto album1Track1 = new TrackDto(1L, "On Your Wings", 233, 1, 1, albumDto1.id());
        albumDto1.tracks().add(album1Track1);
        TrackDto album1Track2 = new TrackDto(2L, "Naked as We Came", 153, 2, 1, albumDto1.id());
        albumDto1.tracks().add(album1Track2);
        TrackDto album1Track3 = new TrackDto(3L, "Cinder and Smoke", 344, 3, 1, albumDto1.id());
        albumDto1.tracks().add(album1Track3);
        return albumDto1;
    }

    public static AlbumDto getAlbum2() {
        AlbumDto albumDto2 = new AlbumDto(2L, "Abbey Road", new ArtistDto(2L, "The Beatles", new HashSet<>()), "https://m.media-amazon.com/images/I/A19M5TR8iYL._AC_SX679_.jpg", LocalDate.of(1969, 9, 26), null, new HashSet<>(), new HashSet<>());
        TrackDto album2Track1 = new TrackDto(4L, "Come Together", 259, 1, 1, albumDto2.id());
        albumDto2.tracks().add(album2Track1);
        TrackDto album2Track2 = new TrackDto(5L, "Something", 182, 2, 1, albumDto2.id());
        albumDto2.tracks().add(album2Track2);
        return albumDto2;
    }

    public static GenreDto getGenre1() {
        return new GenreDto(1L, "folk", null);
    }

    public static GenreDto getGenre2() {
        return new GenreDto(2L, "rock", null);
    }

}
