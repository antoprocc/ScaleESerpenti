package org.example;

import java.util.List;

public class Partita {

    private int numeroGiocatori;
    private Tabellone tabellone;
    private Regole regole;
    private List<Giocatore> giocatori;

    private Partita() {}

    public Partita(int numeroGiocatori, Tabellone tabellone, Regole regole, List<Giocatore> giocatori) {
        this.numeroGiocatori = numeroGiocatori;
        this.tabellone = tabellone;
        this.regole = regole;
        this.giocatori = giocatori;
    }




}
