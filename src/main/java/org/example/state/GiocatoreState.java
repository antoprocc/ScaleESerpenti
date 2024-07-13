package org.example.state;

import org.example.principale.Giocatore;
import org.example.principale.Tabellone;
import org.example.caselle.Casella;
import org.example.dadi.DadoStrategy;

public interface GiocatoreState {

    void eseguiTurno(Giocatore giocatore, Tabellone tabellone, Casella traguardo, DadoStrategy dadoStrategy);
}
