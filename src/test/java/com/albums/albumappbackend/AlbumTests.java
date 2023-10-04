package com.albums.albumappbackend;

import com.albums.albumappbackend.dao.AlbumDao;
import com.albums.albumappbackend.dao.ArtistDao;
import com.albums.albumappbackend.dao.GenreDao;
import com.albums.albumappbackend.dao.TrackDao;
import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.dto.AlbumPlainDto;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.entity.Artist;
import com.albums.albumappbackend.entity.Genre;
import com.albums.albumappbackend.service.AlbumService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AlbumTests {

    @InjectMocks
    private AlbumService albumService;

    @Mock
    private AlbumDao albumDao;

    @Mock
    private TrackDao trackDao;

    @Mock
    private GenreDao genreDao;

    @Mock
    private ArtistDao artistDao;

    private AlbumDto albumDto1;

    private Album album1;

    private AlbumDto albumDto2;

    private Album album2;

    @BeforeEach
    void init() {
        albumDto1 = TestsData.getAlbum1();
        album1 = new Album(albumDto1);
        album1.setGenres(Collections.singleton(new Genre(TestsData.getGenre1())));
        album1.setArtist(new Artist(albumDto1.artist()));
        albumDto2 = TestsData.getAlbum2();
        album2 = new Album(albumDto2);
        album2.setArtist(new Artist(albumDto2.artist()));
    }

    @Test
    public void testCreateAlbum() {
        when(artistDao.findByArtistTitle(any())).thenReturn(Arrays.asList(album1.getArtist()));
        when(genreDao.findAllById(any())).thenReturn(new ArrayList(album1.getGenres()));
        when(albumDao.save(any(Album.class))).thenReturn(album1);

        AlbumDto newAlbum = albumService.create(albumDto1);
        Assertions.assertNotNull(newAlbum);
        Assertions.assertEquals(album1.getTitle(), newAlbum.title());
        Assertions.assertEquals(1, newAlbum.genres().size());
    }

    @Test
    public void testFindAllAlbums() {
        List<Album> albumList = new ArrayList<>();
        albumList.add(album1);
        albumList.add(album2);

        when(albumDao.findAll(Sort.by(Sort.Direction.ASC, "releaseDate", "title"))).thenReturn(albumList);

        List<AlbumPlainDto> albums = albumService.findAll();
        Assertions.assertNotNull(albums);
        Assertions.assertEquals(2, albums.size());
    }

    @Test
    public void testCreateDuplicateAlbumException() {
        when(albumDao.findBy(any(), any(), any(), any())).thenReturn(Arrays.asList(album1));

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            albumService.create(albumDto2);
        });
    }

    @Test
    public void testDeleteAlbum() {
        when(albumDao.findById(album2.getId())).thenReturn(Optional.of(album2));
        doNothing().when(trackDao).deleteAll(album2.getTracks());
        doNothing().when(albumDao).deleteById(album2.getId());

        albumService.delete(albumDto2.id());

        verify(trackDao, times(1)).deleteAll(album2.getTracks()); // Tracks deleted
        verify(albumDao, times(1)).deleteById(album2.getId()); // Album deleted
    }

}
