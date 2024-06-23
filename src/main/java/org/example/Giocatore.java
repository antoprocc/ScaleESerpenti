package org.example;

import org.example.caselle.Casella;
import org.example.caselle.CasellaBase;

import java.util.Objects;

public class Giocatore {

    private Casella casella;
    private String nome;
    private int turno;

    private Giocatore(){};

    public Giocatore(String nome){
        this.nome=nome;
        turno=0;
        casella=new CasellaBase(0,0);
    }

    public Casella getCasella() {
        return casella;
    }

    public void setCasella(Casella casella) {
        this.casella = casella;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
