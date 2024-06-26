package org.example.caselle;

import org.example.Giocatore;
import org.example.dadi.DadoStrategy;

public class CasellaSeprente extends Casella {

    private int destinazione;
    private String tipo;

    public CasellaSeprente(int numeroCasella, int destinazione){
        super();
        tipo= "serpente";
        if( destinazione>=numeroCasella)
            throw new RuntimeException("il serpente nella casella "+numeroCasella+" non è valido");
        this.destinazione=destinazione;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi) {
        giocatore.getCasella().setNumeroCasella(destinazione);
        System.out.println("casella serpente");
    }
}
