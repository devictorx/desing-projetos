package br.edu.ipog.screenmusic.factory;

import br.edu.ipog.screenmusic.model.Album;
import br.edu.ipog.screenmusic.model.Artista;

public class AlbumFactory {
    public static Album criarAlbum(String nome, Artista artista) {
        Album album = new Album();
        album.setNome(nome);
        album.setArtista(artista);
        return album;
    }
}
