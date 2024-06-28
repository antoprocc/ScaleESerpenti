package org.example.caselle;

import org.example.Giocatore;
import org.example.dadi.DadoStrategy;

public class CasellaLocanda extends Casella{

    private String tipo;

    public CasellaLocanda(int numeroCasella){
        super(numeroCasella);
        tipo= "panchina";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi) {
        giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare()+1);
        System.out.println("il giocatore "+giocatore.getNome()+" è finito su una casella panchina");
    }


    public String getTipo() {
        return tipo;
    }

}
