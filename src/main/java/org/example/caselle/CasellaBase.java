package org.example.caselle;

import org.example.Giocatore;
import org.example.dadi.DadoStrategy;

public class CasellaBase extends Casella {

    private String tipo;

    public CasellaBase(int numeroCasella) {
        super();
        tipo="base";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi) {
        System.out.println("Casella base");
    }

    public String getTipo() {
        return tipo;
    }
}
