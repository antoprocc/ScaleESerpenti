package org.example.caselle;

import org.example.Giocatore;
import org.example.Tabellone;
import org.example.dadi.DadoStrategy;

public class CasellaPanchina extends Casella{


    public CasellaPanchina(int numeroCasella){
        super(numeroCasella);
        tipo= "panchina";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare()+1);
    }



    @Override
    public String toString() {return tipo;}

}
