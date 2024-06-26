package org.example.caselle;

import org.example.Giocatore;
import org.example.dadi.DadoStrategy;

public class CasellaMolla extends Casella{

    private String tipo;

    public CasellaMolla(int numeroCasella){
        super();
        tipo= "Molla";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi) {
        muoviGiocatore(giocatore, passi, traguardo, dadoStrategy);
        System.out.println("casella molla");
    }

    private void muoviGiocatore(Giocatore giocatore, int passi, int traguardo, DadoStrategy dadoStrategy) {
        giocatore.muovi(passi, traguardo);
        giocatore.getCasella().effetto(giocatore,dadoStrategy, traguardo, passi);
    }

    public String getTipo() {
        return tipo;
    }
}

