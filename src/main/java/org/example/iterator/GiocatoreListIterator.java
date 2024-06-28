package org.example.iterator;


import org.example.Giocatore;
import java.util.List;

public class GiocatoreListIterator implements GiocatoreIterator{

    private final List<Giocatore> giocatori;
    private int posizione;

    public GiocatoreListIterator(List<Giocatore> giocatori) {
        this.giocatori = giocatori;
        this.posizione = 0;
    }

    @Override
    public boolean hasNext() {
        return !giocatori.isEmpty();
    }

    @Override
    public Giocatore next() {
        Giocatore giocatore = giocatori.get(posizione);
        posizione = (posizione + 1) % giocatori.size(); // Resetta la posizione a 0 quando raggiunge la fine della lista
        return giocatore;
    }
}
