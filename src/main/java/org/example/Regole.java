package org.example;

public class Regole {

    private final int numeroDadi;
    private final int righe;
    private final int colonne;
    private final int numeroGiocatori;
    private final int casellaPanchina;
    private final int casellaLocanda;
    private final int casellaDadi;
    private final int casellaMolla;
    private final int casellaPesca;
    private final boolean DivietoDiSosta;
    private final boolean doppioSei;
    private final boolean unDadoAllaFine;

    public Regole(int righe, int colonne, int numeroDadi, int numeroGiocatori, int casellaPanchina, int casellaLocanda,
                  int casellaDadi, int casellaMolla, int casellaPesca, boolean DivietoDiSosta, boolean doppioSei, boolean unDadoAllaFine) {
        this.righe = righe;
        this.colonne = colonne;
        this.numeroDadi=numeroDadi;
        this.numeroGiocatori=numeroGiocatori;
        this.casellaPanchina = casellaPanchina;
        this.casellaLocanda=casellaLocanda;
        this.casellaDadi = casellaDadi;
        this.casellaMolla= casellaMolla;
        this.casellaPesca = casellaPesca;
        this.DivietoDiSosta = DivietoDiSosta;
        this.doppioSei=doppioSei;
        this.unDadoAllaFine = unDadoAllaFine;
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

    public int getCasellaPanchina() { return casellaPanchina; }

    public int getCasellaDadi() { return casellaDadi; }

    public int getCasellaPesca() { return casellaPesca; }

    public boolean isDivietoDiSosta() { return DivietoDiSosta; }

    public int getCasellaLocanda() {return casellaLocanda;}

    public int getCasellaMolla() {return casellaMolla;}

    public boolean isDoppioSei() {return doppioSei;}

    public boolean isUnDadoAllaFine() {return unDadoAllaFine;}
}
