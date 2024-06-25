package org.example;

public class Regole {

    private int numeroDadi;
    private final int righe;
    private final int colonne;
    private int numeroGiocatori;

    public Regole(int righe, int colonne, int numeroDadi) {
        this.righe = righe;
        this.colonne = colonne;
        this.numeroDadi=numeroDadi;
    }

    public int getNumeroDadi() {
        return numeroDadi;
    }

    public int getRighe() {
        return righe;
    }

    public int getColonne() {
        return colonne;
    }

    public int getNumeroGiocatori() {
        return numeroGiocatori;
    }
}
