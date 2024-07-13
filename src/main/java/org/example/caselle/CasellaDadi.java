package org.example.caselle;

import org.example.principale.Giocatore;
import org.example.principale.Tabellone;
import org.example.dadi.DadoStrategy;

public class CasellaDadi extends Casella{


    public CasellaDadi(int numeroCasella){
        super(numeroCasella);
        tipo= "dadi";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        int passiDaFare;

        do {
            passiDaFare = dadoStrategy.lancia();
            muoviGiocatore(giocatore, passiDaFare, traguardo, dadoStrategy, tabellone);
        } while (passiDaFare == 12);
    }

    private void muoviGiocatore(Giocatore giocatore, int passi, int traguardo, DadoStrategy dadoStrategy, Tabellone tabellone) {
        giocatore.muovi(passi, traguardo, tabellone);
        giocatore.getCasella().effetto(giocatore,dadoStrategy, traguardo, passi, tabellone);
    }


    @Override
    public String toString() {return tipo;}

}
