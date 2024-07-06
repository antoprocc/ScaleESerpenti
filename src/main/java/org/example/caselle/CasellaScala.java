package org.example.caselle;

import org.example.Giocatore;
import org.example.Partita;
import org.example.Tabellone;
import org.example.dadi.DadoStrategy;

public class CasellaScala extends Casella {

    private final int destinazione;

    public CasellaScala(int numeroCasella, int destinazione){
        super(numeroCasella);
        tipo= "scala";
        if( destinazione<=numeroCasella)
            throw new RuntimeException("la scala nella casella "+numeroCasella+" non è valida");
        this.destinazione=destinazione;

    }


    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        Casella nuovaCasella = tabellone.getCasella(destinazione);
        giocatore.setCasella(nuovaCasella);
        String messaggio = "Il giocatore " + giocatore.getNome() + " è finito su una casella " + tipo + " e va alla casella " + destinazione;
        Partita.appendiTestoTurni(messaggio);
    }

    @Override
    public String toString() {return tipo;}
}
