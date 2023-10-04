package com.albums.albumappbackend;

import com.albums.albumappbackend.dao.GenreDao;
import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.dto.GenreDto;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.entity.Artist;
import com.albums.albumappbackend.entity.Genre;
import com.albums.albumappbackend.service.GenreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class GenreTests {

    @InjectMocks
    private GenreService genreService;

    @Mock
    private GenreDao genreDao;

    private GenreDto genreDto1;

    private Genre genre1;

    private GenreDto genreDto2;

    private Genre genre2;

    @BeforeEach
    void init() {
        genreDto1 = TestsData.getGenre1();
        genre1 = new Genre(genreDto1);
        AlbumDto albumDto1 = TestsData.getAlbum1();
        Album album1 = new Album(albumDto1);
        album1.setArtist(new Artist(albumDto1.artist()));
        Set<Album> genre1Albums = new HashSet<>(Arrays.asList(album1));
        genre1.setAlbums(genre1Albums);

        genreDto2 = TestsData.getGenre2();
        genre2 = new Genre(genreDto2);
        AlbumDto albumDto2 = TestsData.getAlbum2();
        Album album2 = new Album(albumDto2);
        album2.setArtist(new Artist(albumDto2.artist()));
        Set<Album> genre2Albums = new HashSet<>(Arrays.asList(album1, album2));
        genre2.setAlbums(genre2Albums);
    }

    @Test
    public void testFindAllGenres() {
        List<Genre> genreList = new ArrayList<>();
        genreList.add(genre1);
        genreList.add(genre2);

        when(genreDao.findAll(Sort.by("title"))).thenReturn(genreList);

        List<GenreDto> genres = genreService.findAll();
        Assertions.assertNotNull(genres);
        Assertions.assertEquals(2, genres.size());

        Assertions.assertEquals(genres.get(0).albums().size(), 1);
        Assertions.assertEquals(genres.get(1).albums().size(), 2);
    }

}
