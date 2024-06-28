package org.example;

import org.example.caselle.Casella;
import org.example.caselle.CasellaBase;

import java.util.Objects;

public class Giocatore {

    private Casella casella;
    private String nome;
    private int turno;
    private int divietoDiSosta;
    private int turniDaSaltare;

    private Giocatore(){}

    public Giocatore(String nome){
        this.nome=nome;
        turno=0;
        casella=new CasellaBase(0);
        divietoDiSosta =0;
        turniDaSaltare=0;
    }

    public void muovi(int passi, int traguardo) {
        int casellaCorrente = this.getCasella().getNumeroCasella();
        int destinazione = casellaCorrente+passi;
        if(destinazione<=traguardo)
            this.casella.setNumeroCasella(destinazione);
        else{
            int indietro=destinazione-traguardo;
            this.casella.setNumeroCasella(traguardo-indietro);
        }
        System.out.println("il giocatore "+getNome()+" va dalla casella "+casellaCorrente+" alla casella "+this.casella.getNumeroCasella());
    }

    public int getTurniDaSaltare() {return turniDaSaltare;}

    public void setTurniDaSaltare(int turniDaSaltare) {this.turniDaSaltare = turniDaSaltare;}

    public int getDivietoDiSosta() {
        return divietoDiSosta;
    }

    public void setDivietoDiSosta(int divietoDiSosta) {this.divietoDiSosta = divietoDiSosta;}

    public Casella getCasella() {
        return casella;
    }

    public void setCasella(Casella casella) {
        this.casella = casella;
    }

    public String getNome() {
        return nome;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Giocatore giocatore)) return false;
        return turno == giocatore.turno && Objects.equals(casella, giocatore.casella) && Objects.equals(nome, giocatore.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(casella, nome, turno);
    }

    @Override
    public String toString() {
        return "Giocatore{" +
                "casella=" + casella +
                ", nome='" + nome + '\'' +
                ", turno=" + turno +
                '}';
    }



}
