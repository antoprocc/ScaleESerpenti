package org.example.caselle;

import org.example.principale.Giocatore;
import org.example.principale.Tabellone;
import org.example.dadi.DadoStrategy;

import java.util.Objects;

public abstract class Casella {

    private int numeroCasella;
    protected String tipo;



    public Casella(){
        numeroCasella=0;
    }

    public Casella(int numeroCasella){
        this.numeroCasella=numeroCasella;
    }


    public int getNumeroCasella() {
        return numeroCasella;
    }

    public void setNumeroCasella(int numeroCasella) {
        this.numeroCasella = numeroCasella;
    }

    public void setTipo(String tipo) {this.tipo = tipo;}

    public String getTipo() {return tipo;}

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

    public abstract void effetto(Giocatore giocatore, DadoStrategy dadoStrategy, int traguardo, int passi, Tabellone tabellone);
}
