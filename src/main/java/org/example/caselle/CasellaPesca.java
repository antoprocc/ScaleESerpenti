package org.example.caselle;

import org.example.Giocatore;
import org.example.Tabellone;
import org.example.dadi.DadoStrategy;

import java.util.Random;

public class CasellaPesca extends Casella{

    private String tipo;

    public CasellaPesca(int numeroCasella){
        super(numeroCasella);
        tipo= "sosta";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        Random random = new Random();
        int carta = random.nextInt(0,4);
        switch (carta){
            case 0: //panchina
                giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare()+1);
                System.out.println("il giocatore "+giocatore.getNome()+" è finito su una casella pesca una carta...Ha pescato: carta panchina");
                break;
            case 1: //locanda
                giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare()+3);
                System.out.println("il giocatore "+giocatore.getNome()+" è finito su una casella pesca una carta...Ha pescato: carta locanda");
                break;
            case 2: //dadi
                int passiDaFare;

                do {
                    passiDaFare = dadoStrategy.lancia();
                    muoviGiocatore(giocatore, passiDaFare, traguardo, dadoStrategy, tabellone);
                } while (passiDaFare == 12);
                System.out.println("il giocatore "+giocatore.getNome()+" è finito su una casella pesca una carta...Ha pescato: carta dadi");
                break;
            case 3: //molla
                muoviGiocatore(giocatore, passi, traguardo, dadoStrategy, tabellone);
                System.out.println("il giocatore "+giocatore.getNome()+" è finito su una casella pesca una carta...Ha pescato: carta molla");
                break;
        }
    }

    private void muoviGiocatore(Giocatore giocatore, int passi, int traguardo, DadoStrategy dadoStrategy, Tabellone tabellone) {
        giocatore.muovi(passi, traguardo,tabellone);
        giocatore.getCasella().effetto(giocatore,dadoStrategy, traguardo, passi, tabellone);
    }

    public String getTipo() {
        return tipo;
    }
}

