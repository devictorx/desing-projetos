package br.edu.ipog.screenmusic.controller;

import br.edu.ipog.screenmusic.model.Album;
import br.edu.ipog.screenmusic.model.Artista;
import br.edu.ipog.screenmusic.model.Musica;
import br.edu.ipog.screenmusic.service.ScreenSoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {

    @Autowired
    private ScreenSoundService screenSoundService;

    @PostMapping("/artista")
    public String cadastrarArtista(@RequestBody Artista artista) {
        screenSoundService.cadastrarArtista(artista);
        return "Artista cadastrado com sucesso!";
    }

    @PostMapping("/album")
    public String cadastrarAlbum(@RequestBody Album album) {
        screenSoundService.adicionarAlbum(album);
        return "Álbum cadastrado com sucesso!";
    }

    @PostMapping("/musica")
    public String adicionarMusica(@RequestBody Musica musica) {
        screenSoundService.adicionarMusica(musica.getTitulo(), musica.getArtista(), musica.getAlbum());
        return "Música adicionada com sucesso!";
    }

    @GetMapping("/artistas")
    public List<Artista> listarArtistas() {
        return screenSoundService.listarArtistas();
    }

    @GetMapping("/albuns/{artistaNome}")
    public List<Album> listarAlbunsPorArtista(@PathVariable String artistaNome) {
        Artista artista = screenSoundService.buscarArtista(artistaNome);
        if (artista != null) {
            return screenSoundService.listarAlbunsPorArtista(artista);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não encontrado");
        }
    }

    @GetMapping
    public List<Musica> listarMusicas() {
        return screenSoundService.listarMusicas();
    }
}
