package org.example.state;

import org.example.principale.Giocatore;
import org.example.principale.Tabellone;
import org.example.caselle.Casella;
import org.example.dadi.DadoStrategy;

import java.util.Random;

import static org.example.principale.Partita.appendiTestoTurni;

public class StatoNormale implements GiocatoreState{



    @Override
    public void eseguiTurno(Giocatore giocatore, Tabellone tabellone, Casella traguardo, DadoStrategy dadoStrategy) {

        int passi;
        Random random = new Random();
        do {
            appendiTestoTurni("\nTurno giocatore " + giocatore.getNome());
            if (tabellone.getRegole().isUnDadoAllaFine() && giocatore.getCasella().getNumeroCasella() > traguardo.getNumeroCasella() - 6) {
                passi = random.nextInt(6) + 1;
                appendiTestoTurni("Il giocatore " + giocatore.getNome() + " tira un solo dado per la regola lancio di un solo dado");
                appendiTestoTurni("dado 1: " + passi);
                muoviGiocatore(giocatore, passi, traguardo.getNumeroCasella(), dadoStrategy, tabellone);
            } else {
                passi = dadoStrategy.lancia();
                muoviGiocatore(giocatore, passi, traguardo.getNumeroCasella(), dadoStrategy, tabellone);
            }
        } while (passi == 12 && tabellone.getRegole().isDoppioSei());
    }


    private void muoviGiocatore(Giocatore giocatore, int passi, int traguardo, DadoStrategy dadoStrategy, Tabellone tabellone) {
        giocatore.muovi(passi, traguardo, tabellone);
        giocatore.getCasella().effetto(giocatore, dadoStrategy, traguardo, passi, tabellone);
    }
}

