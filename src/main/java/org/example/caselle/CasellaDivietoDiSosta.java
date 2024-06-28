package org.example.caselle;

import org.example.Giocatore;
import org.example.dadi.DadoStrategy;

public class CasellaDivietoDiSosta extends Casella{

    private String tipo;

    public CasellaDivietoDiSosta(int numeroCasella){
        super(numeroCasella);
        tipo= "DivietoDiSosta";
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi) {
        giocatore.setDivietoDiSosta(giocatore.getDivietoDiSosta()+1);
        System.out.println("il giocatore "+giocatore.getNome()+" è finito su una casella" +
                "divieto di sosta, riceve carta divieto di sosta");
    }

    public String getTipo() {
        return tipo;
    }
}

