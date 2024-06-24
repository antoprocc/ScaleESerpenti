package org.example;

import org.example.caselle.Casella;
import org.example.caselle.CasellaBase;

import java.util.ArrayList;
import java.util.List;

public final class Partita {

    private static Partita istanza=null;
    private int numeroGiocatori;
    private Tabellone tabellone;
    private List<Giocatore> giocatori;
    private int giocatoreCorrente;
    private Casella traguardo;

    private Partita() {}

    public static synchronized Partita getInstance(){
        if (istanza==null)
            istanza=new Partita();
        return istanza;
    }

    public void configuraPartita(Tabellone tabellone, int numeroGiocatori) {
        this.tabellone = tabellone;
        this.numeroGiocatori = numeroGiocatori;
        this.giocatori = new ArrayList<>();
        this.giocatoreCorrente = 0;
        traguardo=new CasellaBase(tabellone.getRegole().getRighe()* tabellone.getRegole().getColonne());
        inizializzaGiocatori();
    }

    private void inizializzaGiocatori() {
        for (int i = 1; i <= numeroGiocatori; i++) {
            Giocatore giocatore = new Giocatore("P " + i);
            giocatori.add(giocatore);
        }
    }

    private void turno(){
        Giocatore corrente = giocatori.get(giocatoreCorrente);
        int numeroDadi = tabellone.getRegole().getNumeroDadi();
        int passi = lanciaDadi(numeroDadi);
        muoviGiocatore(corrente,passi,traguardo.getNumeroCasella());
        verificaVittoria(corrente);
        //comunica fine del turno TODO
        giocatoreCorrente=(giocatoreCorrente+1)%numeroGiocatori;

    }

    private void verificaVittoria(Giocatore corrente) {
        if(corrente.getCasella().equals(traguardo)){
            //comunica vittoria di corrente
        }
    }

    private void muoviGiocatore(Giocatore corrente, int passi, int traguardo) {
        corrente.muovi(passi, traguardo);
        tabellone.effettoCasella(corrente.getCasella());
    }

    private int lanciaDadi(int numeroDadi) {
        //TODO
        return 0;
    }


}
