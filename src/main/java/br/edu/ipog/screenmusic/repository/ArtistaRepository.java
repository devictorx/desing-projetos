package br.edu.ipog.screenmusic.repository;

import br.edu.ipog.screenmusic.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Artista findByNome(String nome);
}
