package br.edu.ipog.screenmusic.observer;

import java.util.ArrayList;
import java.util.List;

public class MusicaNotifier {
    private List<MusicaObserver> observers = new ArrayList<>();

    public void adicionarObserver(MusicaObserver observer) {
        observers.add(observer);
    }

    public void notificar(String titulo) {
        for (MusicaObserver observer : observers) {
            observer.atualizar(titulo);
        }
    }
}
