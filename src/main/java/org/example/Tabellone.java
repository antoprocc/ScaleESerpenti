package org.example;

import org.example.caselle.*;
import org.example.exceptions.ScalaNonValidaException;
import org.example.exceptions.SerpenteNonValidoException;

import java.util.*;

import static org.example.grafica.PartitaFrame.caselleSpeciali;

public class Tabellone {

    private final Map<Casella, Integer> tabellone;
    private final Map<Integer, Boolean> specializzabili;
    private final Regole regole;

    public Tabellone(Regole regole) {
        this.specializzabili = new HashMap<>();
        this.tabellone = new HashMap<>();
        this.regole = regole;
        inizializzaTabellone();
    }

    private void inizializzaTabellone() {
        int righe = regole.getRighe();
        int colonne = regole.getColonne();

        for (int i = 1; i <= righe * colonne; i++) {
            tabellone.put(new CasellaBase(i), i);
            specializzabili.put(i, true);
        }

        aggiungiScale();
        aggiungiSerpenti();
        aggiungiCaselleSpeciali(regole);
    }

    private void aggiungiSerpenti() {
        int colonne = regole.getColonne();
        Set<Integer> serpenti = this.regole.getSerpenti().keySet();
        for (int bocca : serpenti) {
            int coda = this.regole.getSerpenti().get(bocca);
            if (specializzabili.get(bocca) && specializzabili.get(coda) &&
                    calcolaRiga(bocca, colonne) != calcolaRiga(coda, colonne)) { //non stessa riga
                sostituisciCasella(bocca, new CasellaSerpente(bocca, coda));
                specializzabili.put(bocca, false);
                specializzabili.put(coda, false);
                caselleSpeciali.put(bocca, "serpente");
            } else
                throw new SerpenteNonValidoException();
        }
    }

    private void aggiungiScale() {
        int colonne = regole.getColonne();
        Set<Integer> scale = this.regole.getScale().keySet();
        for (int base : scale) {
            int cima = this.regole.getScale().get(base);
            if (specializzabili.get(base) && specializzabili.get(cima) &&
                    calcolaRiga(base, colonne) != calcolaRiga(cima, colonne)) { //non stessa riga
                sostituisciCasella(base, new CasellaScala(base, cima));
                specializzabili.put(base, false);
                specializzabili.put(cima, false);
                caselleSpeciali.put(base, "scala");
            } else
                throw new ScalaNonValidaException();
        }
    }

    private int calcolaRiga(int posizione, int colonne) {
        return (posizione - 1) / colonne;
    }

    private void aggiungiCaselleSpeciali(Regole regole) {
        Random random = new Random();
        int righe = regole.getRighe();
        int colonne = regole.getColonne();
        int numeroCaselle = righe * colonne;
        int casella;

        // panchina
        for (int i = 0; i < regole.getCasellaPanchina(); i++) {
            do {
                casella = random.nextInt(2, numeroCaselle);
            } while (!specializzabili.get(casella));
            sostituisciCasella(casella, new CasellaPanchina(casella));
            specializzabili.put(casella, false);
            caselleSpeciali.put(casella, "panchina");
        }

        // locanda
        for (int i = 0; i < regole.getCasellaLocanda(); i++) {
            do {
                casella = random.nextInt(2, numeroCaselle);
            } while (!specializzabili.get(casella));
            sostituisciCasella(casella, new CasellaLocanda(casella));
            specializzabili.put(casella, false);
            caselleSpeciali.put(casella, "locanda");
        }

        // dadi
        for (int i = 0; i < regole.getCasellaDadi(); i++) {
            do {
                casella = random.nextInt(2, numeroCaselle);
            } while (!specializzabili.get(casella));
            sostituisciCasella(casella, new CasellaDadi(casella));
            specializzabili.put(casella, false);
            caselleSpeciali.put(casella, "dadi");
        }

        // molla
        for (int i = 0; i < regole.getCasellaMolla(); i++) {
            do {
                casella = random.nextInt(2, numeroCaselle);
            } while (!specializzabili.get(casella));
            sostituisciCasella(casella, new CasellaMolla(casella));
            specializzabili.put(casella, false);
            caselleSpeciali.put(casella, "molla");
        }

        // pesca
        for (int i = 0; i < regole.getCasellaPesca(); i++) {
            do {
                casella = random.nextInt(2, numeroCaselle);
            } while (!specializzabili.get(casella));
            sostituisciCasella(casella, new CasellaPesca(casella));
            specializzabili.put(casella, false);
            caselleSpeciali.put(casella, "pesca");
        }
    }

    private void sostituisciCasella(int posizione, Casella nuovaCasella) {
        Casella casellaDaRimuovere = null;
        for (Map.Entry<Casella, Integer> entry : tabellone.entrySet()) {
            if (entry.getValue().equals(posizione)) {
                casellaDaRimuovere = entry.getKey();
                break;
            }
        }
        if (casellaDaRimuovere != null) {
            tabellone.remove(casellaDaRimuovere);
        }
        tabellone.put(nuovaCasella, posizione);
    }

    public Regole getRegole() {
        return regole;
    }

    public Casella getCasella(int posizione) {
        for (Map.Entry<Casella, Integer> entry : tabellone.entrySet()) {
            if (entry.getValue().equals(posizione)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
