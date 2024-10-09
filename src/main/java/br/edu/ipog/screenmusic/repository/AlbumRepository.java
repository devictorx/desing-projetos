package br.edu.ipog.screenmusic.repository;

import java.util.List;

import br.edu.ipog.screenmusic.model.Album;
import br.edu.ipog.screenmusic.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findAllByArtista(Artista artista);
    Album findByNomeAndArtista(String nome, Artista artista);
    Album findByNome(String nome);
}
