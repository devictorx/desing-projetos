package br.edu.ipog.screenmusic.service;

import br.edu.ipog.screenmusic.model.Album;
import br.edu.ipog.screenmusic.model.Artista;
import br.edu.ipog.screenmusic.model.Musica;
import br.edu.ipog.screenmusic.repository.AlbumRepository;
import br.edu.ipog.screenmusic.repository.ArtistaRepository;
import br.edu.ipog.screenmusic.repository.MusicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ScreenSoundServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private ArtistaRepository artistaRepository;

    @Mock
    private MusicaRepository musicaRepository;

    @InjectMocks
    private ScreenSoundService screenSoundService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrarArtista() {
        Artista artista = new Artista();
        artista.setNome("Artista Teste");

        screenSoundService.cadastrarArtista(artista);

        verify(artistaRepository, times(1)).save(artista);
    }

    @Test
    public void testListarArtistas() {
        List<Artista> artistas = new ArrayList<>();
        artistas.add(new Artista());

        when(artistaRepository.findAll()).thenReturn(artistas);

        List<Artista> resultado = screenSoundService.listarArtistas();
        assertEquals(1, resultado.size());
        verify(artistaRepository, times(1)).findAll();
    }

    @Test
    public void testListarMusicas() {
        List<Musica> musicas = new ArrayList<>();
        musicas.add(new Musica());

        when(musicaRepository.findAll()).thenReturn(musicas);

        List<Musica> resultado = screenSoundService.listarMusicas();
        assertEquals(1, resultado.size());
        verify(musicaRepository, times(1)).findAll();
    }

    @Test
    public void testAdicionarMusicaComAlbumExistente() {
        Artista artista = new Artista();
        artista.setNome("Artista Teste");

        Album album = new Album();
        album.setNome("Album Teste");
        album.setArtista(artista);

        when(albumRepository.findByNome(album.getNome())).thenReturn(album);

        Musica musica = new Musica();
        musica.setTitulo("Musica Teste");
        musica.setArtista(artista);
        musica.setAlbum(album);

        screenSoundService.adicionarMusica("Musica Teste", artista, album);

        verify(musicaRepository, times(1)).save(any(Musica.class));
        verify(albumRepository, never()).save(any(Album.class));
    }

    @Test
    public void testAdicionarMusicaComAlbumNovo() {
        Artista artista = new Artista();
        artista.setNome("Artista Teste");

        Album album = new Album();
        album.setNome("Novo Album");
        album.setArtista(artista);

        when(albumRepository.findByNome(album.getNome())).thenReturn(null);

        screenSoundService.adicionarMusica("Musica Teste", artista, album);

        verify(musicaRepository, times(1)).save(any(Musica.class));
        verify(albumRepository, times(1)).save(any(Album.class));
    }
}
