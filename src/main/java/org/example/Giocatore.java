package org.example;

import org.example.caselle.Casella;
import org.example.caselle.CasellaBase;
import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.state.GiocatoreState;
import org.example.state.StatoNormale;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Giocatore implements Observable {

    private Casella casella;
    private final String nome;
    private int divietoDiSosta;
    private int turniDaSaltare;
    private List<Observer> observers;
    private GiocatoreState stato;

    public Giocatore(String nome){
        this.nome=nome;
        this.casella=new CasellaBase(0);
        this.divietoDiSosta =0;
        this.turniDaSaltare=0;
        this.observers=new ArrayList<>();
        this.stato=new StatoNormale();
    }

    public void muovi(int passi, int traguardo, Tabellone tabellone) {
        int casellaCorrente = this.getCasella().getNumeroCasella();
        int destinazione = casellaCorrente + passi;
        if (destinazione > traguardo) {
            int indietro = destinazione - traguardo;
            destinazione = traguardo - indietro;
        }
        Casella nuovaCasella = tabellone.getCasella(destinazione);
        this.setCasella(nuovaCasella);
        String messaggio= ("il giocatore " + getNome() + " va dalla casella " + casellaCorrente + " alla casella " + destinazione+
                ".\nIl giocatore "+nome+" è finito su una Casella "+casella.toString());
        Partita.appendiTestoTurni(messaggio);
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
        notifyObservers();
    }

    public String getNome() {
        return nome;
    }

    public GiocatoreState getStato() {
        return stato;
    }

    public void setStato(GiocatoreState stato) {
        this.stato = stato;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(casella.getNumeroCasella(), nome);
        }
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
