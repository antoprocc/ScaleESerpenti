package org.example.caselle;

import org.example.Giocatore;
import org.example.dadi.DadoStrategy;

import java.util.Objects;

public abstract class Casella {

    private int riga;
    private int colonna;
    private int numeroCasella;



    public Casella(){
        numeroCasella=0;
    }

    public Casella(int numeroCasella){
        this.numeroCasella=numeroCasella;
    }

    public int getRiga() {
        return riga;
    }

    public void setRiga(int riga) {
        this.riga = riga;
    }

    public int getColonna() {
        return colonna;
    }

    public void setColonna(int colonna) {
        this.colonna = colonna;
    }

    public int getNumeroCasella() {
        return numeroCasella;
    }

    public void setNumeroCasella(int numeroCasella) {
        this.numeroCasella = numeroCasella;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Casella casella)) return false;
        return numeroCasella == casella.numeroCasella;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroCasella);
    }

    public abstract void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi);
}
