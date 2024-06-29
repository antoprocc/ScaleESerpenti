package org.example.caselle;

import org.example.Giocatore;
import org.example.Tabellone;
import org.example.dadi.DadoStrategy;

public class CasellaDivietoDiSosta extends Casella{

    public CasellaDivietoDiSosta(int numeroCasella){
        super(numeroCasella);
        tipo= "DivietoDiSosta";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        giocatore.setDivietoDiSosta(giocatore.getDivietoDiSosta()+1);
    }

    @Override
    public String toString() {return tipo+", riceve carta divieto di sosta";}
}

