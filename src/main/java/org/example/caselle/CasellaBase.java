package org.example.caselle;

import org.example.Giocatore;
import org.example.Tabellone;
import org.example.dadi.DadoStrategy;

public class CasellaBase extends Casella {

    private String tipo;

    public CasellaBase(int numeroCasella) {
        super(numeroCasella);
        tipo="base";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {return tipo;}
}
