package org.example.state;

import org.example.Giocatore;
import org.example.Tabellone;
import org.example.caselle.Casella;
import org.example.dadi.DadoStrategy;

public interface GiocatoreState {

    void eseguiTurno(Giocatore giocatore, Tabellone tabellone, Casella traguardo, DadoStrategy dadoStrategy);
}
