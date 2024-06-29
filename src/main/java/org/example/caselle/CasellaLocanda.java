package org.example.caselle;

import org.example.Giocatore;
import org.example.Tabellone;
import org.example.dadi.DadoStrategy;

public class CasellaLocanda extends Casella{

    private String tipo;

    public CasellaLocanda(int numeroCasella){
        super(numeroCasella);
        tipo= "locanda";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare()+3);
    }


    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {return tipo;}

}
