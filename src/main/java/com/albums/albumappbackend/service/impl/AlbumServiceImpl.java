package com.albums.albumappbackend.service.impl;

import com.albums.albumappbackend.entity.Album;
import com.albums.albumappbackend.repository.AlbumRepository;
import com.albums.albumappbackend.service.AlbumService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }
    @Override
    public Album findByIdWithTracks(Long id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("album.tracks");
        Query query = entityManager.createQuery( "SELECT A FROM Album A WHERE A.id = :id", Album.class)
                .setParameter("id", id)
                .setHint("javax.persistence.loadgraph", entityGraph);

        return (Album) query.getSingleResult();
    }
    @Override
    public List<Album> findByIds(Set<Long> ids) {
        return albumRepository.findAllById(ids);
    }
    @Override
    public List<Album> findByArtist(String artist) {
        Query query = entityManager.createQuery( "SELECT A FROM Album A WHERE LOWER(A.artist) LIKE '%" + artist.toLowerCase() + "%' ORDER BY A.releaseDate" );
        return (List<Album>) query.getResultList( );
    }
    @Override
    public List<Album> findByTitle(String title) {
        Query query = entityManager.createQuery( "SELECT A FROM Album A WHERE LOWER(A.title) LIKE '%" + title.toLowerCase() + "%' ORDER BY A.releaseDate" );
        return (List<Album>) query.getResultList( );
    }
    @Override
    public List<Album> findByArtistAndTitle(String artist, String title) {
        Query query = entityManager.createQuery( "SELECT A FROM Album A WHERE LOWER(A.artist) LIKE '%" + artist.toLowerCase() + "%' AND LOWER(A.title) LIKE '%" + title.toLowerCase() + "%' ORDER BY A.releaseDate" );
        return (List<Album>) query.getSingleResult();
    }
    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }
    @Override
    public void delete(Long id) {
        albumRepository.deleteById(id);
    }
    @Override
    public Album create(Album album) {
        return albumRepository.save(album);
    }
    @Override
    public Album update(Album album) {
        return albumRepository.save(album);
    }
}
