package org.example.state;

import org.example.Giocatore;
import org.example.Tabellone;
import org.example.caselle.Casella;
import org.example.dadi.DadoStrategy;

import java.util.Random;

import static org.example.Partita.appendiTestoTurni;

public class StatoDivietoDiSosta implements GiocatoreState{


    @Override
    public void eseguiTurno(Giocatore giocatore, Tabellone tabellone, Casella traguardo, DadoStrategy dadoStrategy) {
        if (giocatore.getTurniDaSaltare() > 0){
            giocatore.setDivietoDiSosta(giocatore.getDivietoDiSosta() - 1);
            if (giocatore.getDivietoDiSosta()==0)
                giocatore.setStato(new StatoNormale());
            giocatore.setTurniDaSaltare(0);
            appendiTestoTurni("\nIl giocatore " + giocatore.getNome() + " usa carta divieto di sosta e può tirare i dadi");
        }
        //tira i dadi normalmente

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
