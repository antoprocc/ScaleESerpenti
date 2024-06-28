package org.example.caselle;

import org.example.Giocatore;
import org.example.Tabellone;
import org.example.dadi.DadoStrategy;

public class CasellaMolla extends Casella{

    private String tipo;

    public CasellaMolla(int numeroCasella){
        super(numeroCasella);
        tipo= "Molla";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        muoviGiocatore(giocatore, passi, traguardo, dadoStrategy, tabellone);
        System.out.println("il giocatore "+giocatore.getNome()+" è finito su una casella molla");
    }

    private void muoviGiocatore(Giocatore giocatore, int passi, int traguardo, DadoStrategy dadoStrategy, Tabellone tabellone) {
        giocatore.muovi(passi, traguardo,tabellone);
        giocatore.getCasella().effetto(giocatore,dadoStrategy, traguardo, passi, tabellone);
    }

    public String getTipo() {
        return tipo;
    }
}

