package org.example.caselle;

public abstract class Casella {

    private int riga;
    private int colonna;
    private int numeroCasella;
    private boolean specializzabile;

    //(Tabellone.getNumeroCaselle)*riga + colonna + 1;


    public Casella(){
        numeroCasella=0;
        specializzabile=true;
    }

    public Casella(int numeroCasella){
        this.numeroCasella=numeroCasella;
        specializzabile=true;
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
}
