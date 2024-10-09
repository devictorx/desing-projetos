package br.edu.ipog.screenmusic.service;

import br.edu.ipog.screenmusic.model.Album;
import br.edu.ipog.screenmusic.model.Artista;
import br.edu.ipog.screenmusic.model.Musica;
import br.edu.ipog.screenmusic.repository.AlbumRepository;
import br.edu.ipog.screenmusic.repository.ArtistaRepository;
import br.edu.ipog.screenmusic.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScreenSoundService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    public Artista buscarArtista(String nome) {
        return artistaRepository.findByNome(nome);
    }

    public void cadastrarArtista(Artista artista) {
        artistaRepository.save(artista);
    }

    @Transactional(readOnly = true)
    public List<Artista> listarArtistas() {
        return artistaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Musica> listarMusicas() {
        return musicaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Album> listarAlbunsPorArtista(Artista artista) {
        return albumRepository.findAllByArtista(artista);
    }

    @Transactional
    public void adicionarMusica(String titulo, Artista artista, Album album) {
        Album albumExistente = albumRepository.findByNome(album.getNome());
        Artista artistaExistente = artistaRepository.findByNome(artista.getNome());
        if (albumExistente != null && artistaExistente != null) {
            album = albumExistente;
            artista = artistaExistente;
        } else {
            album.setArtista(artista);
            albumRepository.save(album);
        }

        Musica musica = new Musica();
        musica.setTitulo(titulo);
        musica.setArtista(artista);
        musica.setAlbum(album);
        musicaRepository.save(musica);
    }

    @Transactional
    public void adicionarAlbum(Album album) {
        Album albumExistente = albumRepository.findByNomeAndArtista(album.getNome(), album.getArtista());
        if (albumExistente == null) {
            albumRepository.save(album);
        } else {
            System.out.println("Álbum já existe para este artista.");
        }
    }
}
