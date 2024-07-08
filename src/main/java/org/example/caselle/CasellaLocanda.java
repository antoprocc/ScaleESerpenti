package org.example.caselle;

import org.example.Giocatore;
import org.example.Tabellone;
import org.example.dadi.DadoStrategy;
import org.example.state.StatoSosta;

public class CasellaLocanda extends Casella{


    public CasellaLocanda(int numeroCasella){
        super(numeroCasella);
        tipo= "locanda";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare()+3);
        giocatore.setStato(new StatoSosta());
    }



    @Override
    public String toString() {return tipo;}

}
