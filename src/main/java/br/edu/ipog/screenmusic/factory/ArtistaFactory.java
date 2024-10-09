package br.edu.ipog.screenmusic.factory;

import br.edu.ipog.screenmusic.model.Artista;

public class ArtistaFactory {
    public static Artista criarArtista(String nome) {
        Artista artista = new Artista();
        artista.setNome(nome);
        return artista;
    }
}
