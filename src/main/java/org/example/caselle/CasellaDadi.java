package org.example.caselle;

import org.example.Giocatore;
import org.example.dadi.DadoStrategy;

public class CasellaDadi extends Casella{

    private String tipo;

    public CasellaDadi(int numeroCasella){
        super();
        tipo= "dadi";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi) {
        int passiDaFare;

        do {
            passiDaFare = dadoStrategy.lancia();
            muoviGiocatore(giocatore, passiDaFare, traguardo, dadoStrategy);
        } while (passiDaFare == 12);
        System.out.println("casella dadi");
    }

    private void muoviGiocatore(Giocatore giocatore, int passi, int traguardo, DadoStrategy dadoStrategy) {
        giocatore.muovi(passi, traguardo);
        giocatore.getCasella().effetto(giocatore,dadoStrategy, traguardo, passi);
    }

    public String getTipo() {
        return tipo;
    }
}
