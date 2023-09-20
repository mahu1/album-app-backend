package com.albums.albumappbackend;

import com.albums.albumappbackend.dao.AlbumDao;
import com.albums.albumappbackend.dao.ArtistDao;
import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.dto.ArtistDto;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.entity.Artist;
import com.albums.albumappbackend.entity.Track;
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
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ArtistTests {

    @InjectMocks
    private ArtistService artistService;

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
        album1.setTracks(albumDto1.tracks().stream().map(t -> new Track(t)).collect(Collectors.toSet()));
        album1.getTracks().forEach(t -> t.setAlbum(album1));
    }

    @Test
    public void testCreateDuplicateArtistException() {
        Artist artist = album1.getArtist();

        when(artistDao.findByTitle(any())).thenReturn(Arrays.asList(artist));

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            artistService.create(new ArtistDto(artist.getId(), artist.getTitle()));
        });
    }

    @Test
    public void testDeleteUsedArtistException() {
        Artist artist = album1.getArtist();

        when(artistDao.findById(any())).thenReturn(Optional.of(artist));
        when(albumDao.findAlbums(artist.getTitle(), null, null)).thenReturn(Arrays.asList(album1));

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            artistService.delete(album1.getArtist().getId());
        });
    }

}
