package org.example;

import java.util.List;

public final class Partita {

    private static Partita istanza=null;
    private int numeroGiocatori;
    private Tabellone tabellone;
    private Regole regole;
    private List<Giocatore> giocatori;

    private Partita() {}

    public static synchronized Partita getInstance(){
        if (istanza==null)
            istanza=new Partita();
        return istanza;
    }

    //todo





}
