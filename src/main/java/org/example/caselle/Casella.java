package org.example.caselle;

public abstract class Casella {

    private int riga;
    private int colonna;
    private int numeroCasella;

    //(Tabellone.getNumeroCaselle)*riga + colonna + 1;


    public Casella(){
        riga = 0;
        colonna = 0;
    }

    public Casella(int r, int c){
        riga=r;
        colonna=c;
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
