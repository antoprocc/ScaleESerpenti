package org.example.state;

import org.example.Giocatore;
import org.example.Tabellone;
import org.example.caselle.Casella;
import org.example.dadi.DadoStrategy;

import static org.example.Partita.appendiTestoTurni;

public class StatoSosta implements GiocatoreState{

    @Override
    public void eseguiTurno(Giocatore giocatore, Tabellone tabellone, Casella traguardo, DadoStrategy dadoStrategy) {

        giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare() - 1);
        appendiTestoTurni("\nIl giocatore " + giocatore.getNome() + " salta il turno, " + giocatore.getTurniDaSaltare()
                + " turni rimasti da saltare");
        if(giocatore.getTurniDaSaltare()==0)
            giocatore.setStato(new StatoNormale());

    }
}
