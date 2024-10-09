package br.edu.ipog.screenmusic.factory;

import br.edu.ipog.screenmusic.model.Album;
import br.edu.ipog.screenmusic.model.Artista;
import br.edu.ipog.screenmusic.model.Musica;

public class MusicaFactory {
    public static Musica criarMusica(String titulo, Artista artista, Album album) {
        Musica musica = new Musica();
        musica.setTitulo(titulo);
        musica.setArtista(artista);
        musica.setAlbum(album);
        return musica;
    }
}
