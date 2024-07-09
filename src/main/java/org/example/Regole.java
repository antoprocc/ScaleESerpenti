package org.example;

import java.io.Serializable;
import java.util.Map;

public class Regole implements Serializable {

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
    private final Map<Integer,Integer> scale;
    private final Map<Integer,Integer> serpenti;

    public static class Builder{
        //parametri obbigatori
        private final int numeroDadi;
        private final int righe;
        private final int colonne;
        private final int numeroGiocatori;

        //parametri opzionali
        private int casellaPanchina;
        private int casellaLocanda;
        private int casellaDadi;
        private int casellaMolla;
        private int casellaPesca;
        private boolean DivietoDiSosta;
        private boolean doppioSei;
        private boolean unDadoAllaFine;
        private Map scale;
        private Map<Integer,Integer> serpenti;

        public Builder(int numeroDadi, int righe, int colonne, int numeroGiocatori){
            this.numeroDadi=numeroDadi;
            this.righe=righe;
            this.colonne=colonne;
            this.numeroGiocatori=numeroGiocatori;
        }

        public Builder casellaPanchina(int val){
            casellaPanchina=val;
            return this;
        }

        public Builder casellaLocanda(int val){
            casellaLocanda=val;
            return this;
        }

        public Builder casellaDadi(int val){
            casellaDadi=val;
            return this;
        }

        public Builder casellaMolla(int val){
            casellaMolla=val;
            return this;
        }

        public Builder casellaPesca(int val){
            casellaPesca=val;
            return this;
        }

        public Builder DivietoDiSosta(boolean val){
            DivietoDiSosta=val;
            return this;
        }

        public Builder doppioSei(boolean val){
            doppioSei=val;
            return this;
        }

        public Builder unDadoAllaFine(boolean val){
            unDadoAllaFine=val;
            return this;
        }

        public Builder scale(Map<Integer,Integer> val){
            scale=val;
            return this;
        }

        public Builder serpenti(Map<Integer,Integer> val){
            serpenti=val;
            return this;
        }

        public Regole build(){
            return new Regole(this);
        }
    }//Builder



    private Regole(Builder builder){
        this.righe = builder.righe;
        this.colonne = builder.colonne;
        this.numeroDadi=builder.numeroDadi;
        this.numeroGiocatori=builder.numeroGiocatori;
        this.casellaPanchina = builder.casellaPanchina;
        this.casellaLocanda=builder.casellaLocanda;
        this.casellaMolla= builder.casellaMolla;
        this.casellaDadi=builder.casellaDadi;
        this.casellaPesca = builder.casellaPesca;
        this.DivietoDiSosta = builder.DivietoDiSosta;
        this.doppioSei=builder.doppioSei;
        this.unDadoAllaFine = builder.unDadoAllaFine;
        this.scale = builder.scale;
        this.serpenti = builder.serpenti;
    }

    public int getNumeroDadi() {return numeroDadi;}

    public int getRighe() {return righe;}

    public int getColonne() {return colonne;}

    public int getNumeroGiocatori() {return numeroGiocatori;}

    public int getCasellaPanchina() { return casellaPanchina; }

    public int getCasellaDadi() { return casellaDadi; }

    public int getCasellaPesca() { return casellaPesca; }

    public boolean isDivietoDiSosta() { return DivietoDiSosta; }

    public int getCasellaLocanda() {return casellaLocanda;}

    public int getCasellaMolla() {return casellaMolla;}

    public boolean isDoppioSei() {return doppioSei;}

    public boolean isUnDadoAllaFine() {return unDadoAllaFine;}

    public Map<Integer, Integer> getScale() {
        return scale;
    }

    public Map<Integer, Integer> getSerpenti() {
        return serpenti;
    }
}
