package org.example.caselle;

import org.example.Giocatore;
import org.example.Partita;
import org.example.Tabellone;
import org.example.dadi.DadoStrategy;

public class CasellaSerpente extends Casella {

    private final int destinazione;

    public CasellaSerpente(int numeroCasella, int destinazione){
        super(numeroCasella);
        tipo= "serpente";
        if( destinazione>=numeroCasella)
            throw new RuntimeException("il serpente nella casella "+numeroCasella+" non è valido");
        this.destinazione=destinazione;
    }


    @Override
    public void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone) {
        giocatore.getCasella().setNumeroCasella(destinazione);
        String messaggio = "Il giocatore " + giocatore.getNome() + " è finito su una casella " + tipo + " e va alla casella " + destinazione;
        System.out.println(messaggio);
        Partita.appendiTestoTurni(messaggio);
    }

    @Override
    public String toString() {return tipo;}
}
