package org.example.caselle;

import org.example.Giocatore;
import org.example.Tabellone;
import org.example.dadi.DadoStrategy;

public class CasellaMolla extends Casella{


    public CasellaMolla(int numeroCasella){
        super(numeroCasella);
        tipo= "molla";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        muoviGiocatore(giocatore, passi, traguardo, dadoStrategy, tabellone);

    }

    private void muoviGiocatore(Giocatore giocatore, int passi, int traguardo, DadoStrategy dadoStrategy, Tabellone tabellone) {
        giocatore.muovi(passi, traguardo,tabellone);
        giocatore.getCasella().effetto(giocatore,dadoStrategy, traguardo, passi, tabellone);
    }


    @Override
    public String toString() {return tipo;}
}

