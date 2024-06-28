package org.example.caselle;

import org.example.Giocatore;
import org.example.Tabellone;
import org.example.dadi.DadoStrategy;

public class CasellaScala extends Casella {

    private int destinazione;
    private String tipo;

    public CasellaScala(int numeroCasella, int destinazione){
        super(numeroCasella);
        tipo= "scala";
        if( destinazione<=numeroCasella)
            throw new RuntimeException("la scala nella casella "+numeroCasella+" non è valida");
        this.destinazione=destinazione;

    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        giocatore.getCasella().setNumeroCasella(destinazione);
        System.out.println("il giocatore "+giocatore.getNome()+" è finito su una casella scala ed" +
                "è andato sulla casella "+destinazione);
    }
}
