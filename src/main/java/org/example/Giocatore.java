package org.example;

import org.example.caselle.Casella;
import org.example.caselle.CasellaBase;

import java.util.Objects;

public class Giocatore {

    private Casella casella;
    private final String nome;
    private int divietoDiSosta;
    private int turniDaSaltare;

    public Giocatore(String nome){
        this.nome=nome;
        casella=new CasellaBase(0);
        divietoDiSosta =0;
        turniDaSaltare=0;
    }

    public void muovi(int passi, int traguardo, Tabellone tabellone) {
        int casellaCorrente = this.getCasella().getNumeroCasella();
        int destinazione = casellaCorrente + passi;
        if (destinazione > traguardo) {
            int indietro = destinazione - traguardo;
            destinazione = traguardo - indietro;
        }
        this.casella.setNumeroCasella(destinazione);
        // Ottieni la casella effettiva dal tabellone
        Casella nuovaCasella = tabellone.getCasella(destinazione);
        this.setCasella(nuovaCasella);
        System.out.println("il giocatore " + getNome() + " va dalla casella " + casellaCorrente + " alla casella " + destinazione+
                " il giocatore "+nome+" è finito su una Casella "+casella.toString());
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Giocatore giocatore)) return false;
        return Objects.equals(casella, giocatore.casella) && Objects.equals(nome, giocatore.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(casella, nome);
    }

    @Override
    public String toString() {
        return "Giocatore{" +
                "casella=" + casella +
                ", nome='" + nome + '\'' +
                '}';
    }



}
