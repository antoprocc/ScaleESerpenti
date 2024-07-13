package org.example.caselle;

import org.example.principale.Giocatore;
import org.example.principale.Tabellone;
import org.example.dadi.DadoStrategy;

public class CasellaBase extends Casella {


    public CasellaBase(int numeroCasella) {
        super(numeroCasella);
        tipo="base";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
    }

    @Override
    public String toString() {return tipo;}
}
