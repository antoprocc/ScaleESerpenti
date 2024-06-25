package org.example;

import org.example.caselle.Casella;
import org.example.caselle.CasellaBase;
import org.example.dadi.DadoDoppioStrategy;
import org.example.dadi.DadoSingoloStrategy;
import org.example.dadi.DadoStrategy;

import java.util.ArrayList;
import java.util.List;

public final class Partita {

    private static Partita istanza=null;
    private Tabellone tabellone;
    private List<Giocatore> giocatori;
    private int giocatoreCorrente;
    private Casella traguardo;
    private DadoStrategy dadoStrategy;

    private Partita(Tabellone tabellone) {
        this.tabellone = tabellone;
        this.giocatori = new ArrayList<>();
        this.giocatoreCorrente = 0;
        traguardo=new CasellaBase(tabellone.getRegole().getRighe()* tabellone.getRegole().getColonne());

        if(tabellone.getRegole().getNumeroDadi()==1)
            this.dadoStrategy=new DadoSingoloStrategy();
        else if (tabellone.getRegole().getNumeroDadi()==2)
            this.dadoStrategy=new DadoDoppioStrategy();
        else
            //notifica errore nel numero dei dadi TODO

            inizializzaGiocatori();
    }

    public static synchronized Partita getInstance(Tabellone tabellone, int numeroGiocatori){
        if (istanza==null)
            istanza=new Partita(tabellone);
        return istanza;
    }


    private void inizializzaGiocatori() {
        int numeroGiocatori = this.tabellone.getRegole().getNumeroGiocatori();
        for (int i = 1; i <= numeroGiocatori; i++) {
            Giocatore giocatore = new Giocatore("P " + i);
            giocatori.add(giocatore);
        }
    }

    private void turno() {
        int numeroGiocatori = this.tabellone.getRegole().getNumeroGiocatori();
        Giocatore giocatore = giocatori.get(giocatoreCorrente);
        int passi;

        do {
            passi = dadoStrategy.lancia();
            muoviGiocatore(giocatore, passi, traguardo.getNumeroCasella());
        } while (passi == 12);

        verificaVittoria(giocatore);
        //comunica fine del turno TODO
        giocatoreCorrente = (giocatoreCorrente + 1) % numeroGiocatori;

    }

    private void verificaVittoria(Giocatore giocatore) {
        if(giocatore.getCasella().equals(traguardo)){
            //comunica vittoria di corrente TODO
        }
    }

    private void muoviGiocatore(Giocatore giocatore, int passi, int traguardo) {
        giocatore.muovi(passi, traguardo);
        tabellone.effettoCasella(giocatore.getCasella());
    }


}
