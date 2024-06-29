package org.example.caselle;

import org.example.Giocatore;
import org.example.Tabellone;
import org.example.dadi.DadoStrategy;

public class CasellaSerpente extends Casella {

    private int destinazione;
    private String tipo;

    public CasellaSerpente(int numeroCasella, int destinazione){
        super(numeroCasella);
        tipo= "serpente";
        if( destinazione>=numeroCasella)
            throw new RuntimeException("il serpente nella casella "+numeroCasella+" non è valido");
        this.destinazione=destinazione;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        giocatore.getCasella().setNumeroCasella(destinazione);

    }

    @Override
    public String toString() {return tipo+ " è andato sulla casella "+destinazione;}
}
