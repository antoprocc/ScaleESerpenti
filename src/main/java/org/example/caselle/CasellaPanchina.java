package org.example.caselle;

import org.example.principale.Giocatore;
import org.example.principale.Tabellone;
import org.example.dadi.DadoStrategy;
import org.example.state.StatoSosta;

public class CasellaPanchina extends Casella{


    public CasellaPanchina(int numeroCasella){
        super(numeroCasella);
        tipo= "panchina";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare()+1);
        giocatore.setStato(new StatoSosta());
    }



    @Override
    public String toString() {return tipo;}

}
