package com.albums.albumappbackend;

import com.albums.albumappbackend.dao.TrackDao;
import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.dto.TrackDto;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.entity.Track;
import com.albums.albumappbackend.service.TrackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TrackTests {

    @InjectMocks
    private TrackService trackService;

    @Mock
    private TrackDao trackDao;

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
    public void testDeleteAlbumTrack() {
        TrackDto firstTrackDto = albumDto1.tracks().stream().filter(t -> t.trackNumber() == 1).findFirst().get();
        Track firstTrack = album1.getTracks().stream().filter(t -> t.getTrackNumber() == 1).findFirst().get();
        Track secondTrack = album1.getTracks().stream().filter(t -> t.getTrackNumber() == 2).findFirst().get();
        Track thirdTrack = album1.getTracks().stream().filter(t -> t.getTrackNumber() == 3).findFirst().get();

        when(trackDao.findById(firstTrack.getId())).thenReturn(Optional.of(firstTrack));
        when(trackDao.findById(secondTrack.getId())).thenReturn(Optional.of(secondTrack));
        when(trackDao.findById(thirdTrack.getId())).thenReturn(Optional.of(thirdTrack));
        doNothing().when(trackDao).deleteById(any());

        trackService.delete(firstTrackDto.id());

        Assertions.assertEquals(1, secondTrack.getTrackNumber()); // Track number changed 2 -> 1
        Assertions.assertEquals(2, thirdTrack.getTrackNumber()); // Track number changed 3 -> 2
        verify(trackDao, times(1)).deleteById(firstTrack.getId()); // First track deleted
    }

}
