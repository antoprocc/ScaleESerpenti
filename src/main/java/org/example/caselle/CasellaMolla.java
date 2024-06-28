package org.example.caselle;

import org.example.Giocatore;
import org.example.dadi.DadoStrategy;

public class CasellaMolla extends Casella{

    private String tipo;

    public CasellaMolla(int numeroCasella){
        super(numeroCasella);
        tipo= "Molla";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi) {
        muoviGiocatore(giocatore, passi, traguardo, dadoStrategy);
        System.out.println("il giocatore "+giocatore.getNome()+" è finito su una casella molla");
    }

    private void muoviGiocatore(Giocatore giocatore, int passi, int traguardo, DadoStrategy dadoStrategy) {
        giocatore.muovi(passi, traguardo);
        giocatore.getCasella().effetto(giocatore,dadoStrategy, traguardo, passi);
    }

    public String getTipo() {
        return tipo;
    }
}

