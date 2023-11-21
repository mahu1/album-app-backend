package com.albums.albumappbackend;

import com.albums.albumappbackend.dao.AlbumDao;
import com.albums.albumappbackend.dao.GenreDao;
import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.dto.ArtistDto;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class GenreTests {

    @InjectMocks
    private GenreService genreService;

    @Mock
    private GenreDao genreDao;

    @Mock
    private AlbumDao albumDao;

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
        album1.setGenres(Stream.of(genre1).collect(Collectors.toCollection(HashSet::new)));
        Set<Album> genre1Albums = new HashSet<>(Arrays.asList(album1));
        genre1.setAlbums(genre1Albums);

        genreDto2 = TestsData.getGenre2();
        genre2 = new Genre(genreDto2);
        AlbumDto albumDto2 = TestsData.getAlbum2();
        Album album2 = new Album(albumDto2);
        album2.setArtist(new Artist(albumDto2.artist()));
        album2.setGenres(Stream.of(genre2).collect(Collectors.toCollection(HashSet::new)));
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

    @Test
    public void testDeleteGenre() {
        when(genreDao.findById(any())).thenReturn(Optional.of(genre1));
        when(albumDao.findByGenreId(any())).thenReturn(genre1.getAlbums().stream().toList());
        doNothing().when(genreDao).deleteById(genre1.getId());

        genreService.delete(genre1.getId());

        Assertions.assertEquals(0, genre1.getAlbums().stream().findFirst().get().getGenres().size()); // Genre removed from album
    }

    @Test
    public void testCreateDuplicateGenreException() {
        when(genreDao.findByGenreTitle(any())).thenReturn(Arrays.asList(genre1));

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            genreService.create(new GenreDto(genre1.getId(), genre1.getTitle(), null));
        });
    }

}
