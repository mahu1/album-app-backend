package com.albums.albumappbackend;

import com.albums.albumappbackend.dao.AlbumDao;
import com.albums.albumappbackend.dao.ArtistDao;
import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.dto.ArtistDto;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.entity.Artist;
import com.albums.albumappbackend.service.AlbumService;
import com.albums.albumappbackend.service.ArtistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ArtistTests {

    @InjectMocks
    private ArtistService artistService;

    @Mock
    private AlbumService albumService;

    @Mock
    private ArtistDao artistDao;

    @Mock
    private AlbumDao albumDao;

    private AlbumDto albumDto1;

    private Album album1;

    @BeforeEach
    void init() {
        albumDto1 = TestsData.getAlbum1();
        album1 = new Album(albumDto1);
        album1.setArtist(new Artist(albumDto1.artist()));
    }

    @Test
    public void testCreateDuplicateArtistException() {
        Artist artist = album1.getArtist();

        when(artistDao.findByTitle(any())).thenReturn(Arrays.asList(artist));

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            artistService.create(new ArtistDto(artist.getId(), artist.getTitle(), null));
        });
    }

    @Test
    public void testDeleteArtist() {
        Artist artist = album1.getArtist();

        when(artistDao.findById(any())).thenReturn(Optional.of(artist));
        when(albumDao.findAlbums(any(), any(), any(), any())).thenReturn(Arrays.asList(album1));
        doNothing().when(albumService).delete(album1.getId());
        doNothing().when(artistDao).deleteById(artist.getId());

        artistService.delete(artist.getId());

        verify(albumService, times(1)).delete(artist.getId()); // Albums deleted
        verify(artistDao, times(1)).deleteById(artist.getId()); // Artist deleted
    }

}
