package org.example.caselle;

import org.example.Giocatore;
import org.example.Tabellone;
import org.example.dadi.DadoStrategy;

import java.util.Random;

public class CasellaPesca extends Casella {

    private String tipoPescato;

    public CasellaPesca(int numeroCasella) {
        super(numeroCasella);
        this.tipo = "pesca una carta";
        this.tipoPescato = "";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        Random random = new Random();
        int carta = random.nextInt(0,4);
        switch (carta) {
            case 0: // panchina
                tipoPescato = "panchina";
                System.out.println("... Ha pescato: carta " + tipoPescato);
                giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare() + 1);
                break;
            case 1: // locanda
                tipoPescato = "locanda";
                System.out.println("... Ha pescato: carta " + tipoPescato);
                giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare() + 3);
                break;
            case 2: // dadi
                tipoPescato = "dadi";
                System.out.println("... Ha pescato: carta " + tipoPescato);
                int passiDaFare;
                do {
                    passiDaFare = dadoStrategy.lancia();
                    muoviGiocatore(giocatore, passiDaFare, traguardo, dadoStrategy, tabellone);
                } while (passiDaFare == 12);
                break;
            case 3: // molla
                tipoPescato = "molla";
                System.out.println("... Ha pescato: carta " + tipoPescato);
                muoviGiocatore(giocatore, passi, traguardo, dadoStrategy, tabellone);
                break;
        }
    }

    private void muoviGiocatore(Giocatore giocatore, int passi, int traguardo, DadoStrategy dadoStrategy, Tabellone tabellone) {
        giocatore.muovi(passi, traguardo, tabellone);
        giocatore.getCasella().effetto(giocatore, dadoStrategy, traguardo, passi, tabellone);
    }


    @Override
    public String toString() {
        return tipo ;
    }
}
