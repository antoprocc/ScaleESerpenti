package org.example.caselle;

import org.example.Giocatore;
import org.example.Partita;
import org.example.Tabellone;
import org.example.dadi.DadoStrategy;
import org.example.state.StatoDivietoDiSosta;
import org.example.state.StatoSosta;

import java.util.Random;

public class CasellaPesca extends Casella {

    private String tipoPescato;

    public CasellaPesca(int numeroCasella) {
        super(numeroCasella);
        this.tipo = "pesca";
        this.tipoPescato = "";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        String messaggio;
        Random random = new Random();
        if (tabellone.getRegole().isDivietoDiSosta()){
            int carta = random.nextInt(0,5);
            switch (carta) {
                case 0: // panchina
                    tipoPescato = "panchina";
                    messaggio=("... Ha pescato: carta " + tipoPescato);
                    Partita.appendiTestoTurni(messaggio);
                    giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare() + 1);
                    giocatore.setStato(new StatoSosta());
                    break;
                case 1: // locanda
                    tipoPescato = "locanda";
                    messaggio=("... Ha pescato: carta " + tipoPescato);
                    Partita.appendiTestoTurni(messaggio);
                    giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare() + 3);
                    giocatore.setStato(new StatoSosta());
                    break;
                case 2: // dadi
                    tipoPescato = "dadi";
                    messaggio=("... Ha pescato: carta " + tipoPescato);
                    Partita.appendiTestoTurni(messaggio);
                    int passiDaFare;
                    do {
                        passiDaFare = dadoStrategy.lancia();
                        muoviGiocatore(giocatore, passiDaFare, traguardo, dadoStrategy, tabellone);
                    } while (passiDaFare == 12);
                    break;
                case 3: // molla
                    tipoPescato = "molla";
                    messaggio=("... Ha pescato: carta " + tipoPescato);
                    Partita.appendiTestoTurni(messaggio);
                    muoviGiocatore(giocatore, passi, traguardo, dadoStrategy, tabellone);
                    break;
                case 4: // divieto di sosta
                    tipoPescato = "divieto di sosta";
                    messaggio=("... Ha pescato: carta " + tipoPescato);
                    Partita.appendiTestoTurni(messaggio);
                    giocatore.setDivietoDiSosta(giocatore.getDivietoDiSosta()+1);
                    giocatore.setStato(new StatoDivietoDiSosta());
                    break;
            }
        }
        else{
            int carta = random.nextInt(0,4);
            switch (carta) {
                case 0: // panchina
                    tipoPescato = "panchina";
                    messaggio=("... Ha pescato: carta " + tipoPescato);
                    Partita.appendiTestoTurni(messaggio);
                    giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare() + 1);
                    giocatore.setStato(new StatoSosta());
                    break;
                case 1: // locanda
                    tipoPescato = "locanda";
                    messaggio=("... Ha pescato: carta " + tipoPescato);
                    Partita.appendiTestoTurni(messaggio);
                    giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare() + 3);
                    giocatore.setStato(new StatoSosta());
                    break;
                case 2: // dadi
                    tipoPescato = "dadi";
                    messaggio=("... Ha pescato: carta " + tipoPescato);
                    Partita.appendiTestoTurni(messaggio);
                    int passiDaFare;
                    do {
                        passiDaFare = dadoStrategy.lancia();
                        muoviGiocatore(giocatore, passiDaFare, traguardo, dadoStrategy, tabellone);
                    } while (passiDaFare == 12);
                    break;
                case 3: // molla
                    tipoPescato = "molla";
                    messaggio=("... Ha pescato: carta " + tipoPescato);
                    Partita.appendiTestoTurni(messaggio);
                    muoviGiocatore(giocatore, passi, traguardo, dadoStrategy, tabellone);
                    break;
            }
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
