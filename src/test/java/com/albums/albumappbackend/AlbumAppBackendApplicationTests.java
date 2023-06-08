package com.albums.albumappbackend;

import com.albums.albumappbackend.dao.AlbumDao;
import com.albums.albumappbackend.dao.TrackDao;
import com.albums.albumappbackend.dto.AlbumDto;
import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.entity.Track;
import com.albums.albumappbackend.service.impl.AlbumServiceImpl;
import com.albums.albumappbackend.service.impl.TrackServiceImpl;
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

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AlbumAppBackendApplicationTests {

	@InjectMocks
	private AlbumServiceImpl albumService;

	@InjectMocks
	private TrackServiceImpl trackService;

	@Mock
	private AlbumDao albumDao;

	@Mock
	private TrackDao trackDao;

	private Album album1;
	private Album album2;

	@BeforeEach
	void init() {
		AlbumDto albumDto1 = new AlbumDto(1L, "Iron & Wine", "Our Endless Numbered Days", "https://m.media-amazon.com/images/I/61Xzf+RI9sL._AC_.jpg", LocalDate.of(2004, 3, 23));
		album1 = new Album(albumDto1);
		Track album1Track1 = new Track();
		album1Track1.setId(1L);
		album1Track1.setTitle("On Your Wings");
		album1Track1.setLength("3:53");
		album1Track1.setTrackNumber(1);
		album1Track1.setAlbum(album1);
		album1.getTracks().add(album1Track1);
		Track album1Track2 = new Track();
		album1Track2.setId(2L);
		album1Track2.setTitle("Naked as We Came");
		album1Track2.setLength("2:33");
		album1Track2.setTrackNumber(2);
		album1Track2.setAlbum(album1);
		album1.getTracks().add(album1Track2);
		Track album1Track3 = new Track();
		album1Track3.setId(3L);
		album1Track3.setTitle("Cinder and Smoke");
		album1Track3.setLength("5:44");
		album1Track3.setTrackNumber(3);
		album1Track3.setAlbum(album1);
		album1.getTracks().add(album1Track3);

		AlbumDto albumDto2 = new AlbumDto(2L, "Abbey Road", "The Beatles", "https://m.media-amazon.com/images/I/A19M5TR8iYL._AC_SX679_.jpg", LocalDate.of(1969, 9, 26));
		album2 = new Album(albumDto2);
		Track album2Track1 = new Track();
		album2Track1.setId(4L);
		album2Track1.setTitle("Come Together");
		album2Track1.setLength("4:19");
		album2Track1.setTrackNumber(1);
		album2Track1.setAlbum(album2);
		album2.getTracks().add(album2Track1);
		Track album2Track2 = new Track();
		album2Track2.setId(5L);
		album2Track2.setTitle("Something");
		album2Track2.setLength("3:02");
		album2Track2.setTrackNumber(2);
		album2Track2.setAlbum(album2);
		album2.getTracks().add(album2Track2);
	}

	@Test
	public void testCreateAlbum() {
		when(albumDao.save(any(Album.class))).thenReturn(album1);

		Album newAlbum = albumService.create(album1);
		Assertions.assertNotNull(newAlbum);
		Assertions.assertEquals(album1.getTitle(), newAlbum.getTitle());
	}

	@Test
	public void testFindAllAlbums() {
		List<Album> albumList = new ArrayList<>();
		albumList.add(album1);
		albumList.add(album2);

		when(albumDao.findAll(Sort.by(Sort.Direction.ASC, "releaseDate"))).thenReturn(albumList);

		List<Album> albums = albumService.findAll();
		Assertions.assertNotNull(albums);
		Assertions.assertEquals(2, albums.size());
	}

	@Test
	public void testDuplicateAlbumException() {
		when(albumDao.findByArtistAndTitle(any(), any())).thenReturn(Arrays.asList(album1));

		Assertions.assertThrows(ResponseStatusException.class, () -> {
			albumService.create(album2);
		});
	}

	@Test
	public void testDeleteAlbum() {
		Set<Track> tracks = album2.getTracks();
		Track firstTrack = tracks.stream().filter(t -> t.getTrackNumber().equals(1)).findFirst().get();
		Track secondTrack = tracks.stream().filter(t -> t.getTrackNumber().equals(2)).findFirst().get();

		when(albumDao.findById(album2.getId())).thenReturn(Optional.of(album2));
		doNothing().when(albumDao).deleteById(album2.getId());
		doNothing().when(trackDao).deleteById(firstTrack.getId());
		doNothing().when(trackDao).deleteById(secondTrack.getId());

		albumService.delete(album2.getId());

		verify(albumDao, times(1)).deleteById(album2.getId()); // Album deleted
		verify(trackDao, times(1)).deleteById(firstTrack.getId()); // First track deleted
		verify(trackDao, times(1)).deleteById(secondTrack.getId()); // Second track deleted
	}

	@Test
	public void testDeleteAlbumTrack() {
		Set<Track> tracks = album1.getTracks();
		Track firstTrack = tracks.stream().filter(t -> t.getTrackNumber().equals(1)).findFirst().get();
		Track secondTrack = tracks.stream().filter(t -> t.getTrackNumber().equals(2)).findFirst().get();
		Track thirdTrack = tracks.stream().filter(t -> t.getTrackNumber().equals(3)).findFirst().get();

		when(trackDao.findById(firstTrack.getId())).thenReturn(Optional.of(firstTrack));
		when(trackDao.findById(secondTrack.getId())).thenReturn(Optional.of(secondTrack));
		when(trackDao.findById(thirdTrack.getId())).thenReturn(Optional.of(thirdTrack));
		when(trackDao.save(secondTrack)).thenReturn(secondTrack);
		when(trackDao.save(thirdTrack)).thenReturn(thirdTrack);
		doNothing().when(trackDao).deleteById(any());

		trackService.delete(firstTrack.getId());

		Assertions.assertEquals(1, secondTrack.getTrackNumber()); // Track number changed 2 -> 1
		Assertions.assertEquals(2, thirdTrack.getTrackNumber()); // Track number changed 3 -> 2
		verify(trackDao, times(1)).deleteById(firstTrack.getId()); // First track deleted
	}

}
