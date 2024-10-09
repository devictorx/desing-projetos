package br.edu.ipog.screenmusic.repository;

import br.edu.ipog.screenmusic.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
}
