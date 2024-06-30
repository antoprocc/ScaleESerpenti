package org.example;

import org.example.caselle.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

        //decido di inserire una scala e un serpente in proporzione 1/8 ciascuno rispetto al numero di caselle (come nella maggior parte dei tabelloni)
        int nSerpenti = righe * colonne * 8 / 100; //uguale per le scale

        aggiungiScaleESerpenti(nSerpenti);
        aggiungiCaselleSpeciali(regole);
    }

    private void aggiungiScaleESerpenti(int nSerpenti) {
        Random random = new Random();
        int righe = regole.getRighe();
        int colonne = regole.getColonne();
        int numeroCaselle = righe * colonne;

        // SERPENTI
        for (int i = 0; i < nSerpenti; i++) {
            int bocca, coda;
            do {
                bocca = random.nextInt(2, numeroCaselle);
                coda = random.nextInt(1, bocca);
            } while (!specializzabili.get(bocca) || !specializzabili.get(coda) ||
                    calcolaRiga(bocca, colonne) == calcolaRiga(coda, colonne));

            sostituisciCasella(bocca, new CasellaSerpente(bocca, coda));
            specializzabili.put(bocca, false);
            specializzabili.put(coda, false);
            System.out.println("la casella " + bocca + " è un serpente");
        }

        // SCALE
        for (int i = 0; i < nSerpenti; i++) {
            int base, cima;
            do {
                base = random.nextInt(1, numeroCaselle - 1);
                cima = random.nextInt(base + 1, numeroCaselle);
            } while (!specializzabili.get(base) || !specializzabili.get(cima) ||
                    calcolaRiga(base, colonne) == calcolaRiga(cima, colonne));

            sostituisciCasella(base, new CasellaScala(base, cima));
            specializzabili.put(base, false);
            specializzabili.put(cima, false);
            System.out.println("la casella " + base + " è una scala");
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
            System.out.println("la casella " + casella + " è una panchina");
        }

        // locanda
        for (int i = 0; i < regole.getCasellaLocanda(); i++) {
            do {
                casella = random.nextInt(2, numeroCaselle);
            } while (!specializzabili.get(casella));
            sostituisciCasella(casella, new CasellaLocanda(casella));
            specializzabili.put(casella, false);
            System.out.println("la casella " + casella + " è una locanda");
        }

        // dadi
        for (int i = 0; i < regole.getCasellaDadi(); i++) {
            do {
                casella = random.nextInt(2, numeroCaselle);
            } while (!specializzabili.get(casella));
            sostituisciCasella(casella, new CasellaDadi(casella));
            specializzabili.put(casella, false);
            System.out.println("la casella " + casella + " è una casella dadi");
        }

        // molla
        for (int i = 0; i < regole.getCasellaMolla(); i++) {
            do {
                casella = random.nextInt(2, numeroCaselle);
            } while (!specializzabili.get(casella));
            sostituisciCasella(casella, new CasellaMolla(casella));
            specializzabili.put(casella, false);
            System.out.println("la casella " + casella + " è una molla");
        }

        // pesca
        for (int i = 0; i < regole.getCasellaPesca(); i++) {
            do {
                casella = random.nextInt(2, numeroCaselle);
            } while (!specializzabili.get(casella));
            sostituisciCasella(casella, new CasellaPesca(casella));
            specializzabili.put(casella, false);
            System.out.println("la casella " + casella + " è una casella pesca");
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
