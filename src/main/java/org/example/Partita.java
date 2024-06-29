package org.example;

import org.example.caselle.Casella;
import org.example.caselle.CasellaBase;
import org.example.dadi.DadoDoppioStrategy;
import org.example.dadi.DadoSingoloStrategy;
import org.example.dadi.DadoStrategy;
import org.example.iterator.GiocatoreIterator;
import org.example.iterator.GiocatoreListIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public final class Partita {

    private static Partita istanza = null;
    private final Tabellone tabellone;
    private final List<Giocatore> giocatori;
    private final GiocatoreIterator giocatoreIterator;
    private final Casella traguardo;
    private DadoStrategy dadoStrategy;
    private boolean finita;
    private Giocatore vincitore;
    private final boolean automatica;

    private Partita(Tabellone tabellone, boolean automatica) {
        this.tabellone = tabellone;
        this.giocatori = new ArrayList<>();
        this.giocatoreIterator = new GiocatoreListIterator(giocatori);
        this.finita = false;
        this.vincitore = null;
        this.automatica = automatica;
        traguardo = new CasellaBase(tabellone.getRegole().getRighe() * tabellone.getRegole().getColonne());

        if (tabellone.getRegole().getNumeroDadi() == 1)
            this.dadoStrategy = new DadoSingoloStrategy();
        else if (tabellone.getRegole().getNumeroDadi() == 2)
            this.dadoStrategy = new DadoDoppioStrategy();
        else
            System.out.println("numero dadi non corretto");
        //notifica errore nel numero dei dadi TODO
    }

    public static synchronized Partita getInstance(Tabellone tabellone, boolean automatica) {
        if (istanza == null)
            istanza = new Partita(tabellone, automatica);
        return istanza;
    }

    private void inizializzaGiocatori() {
        int numeroGiocatori = this.tabellone.getRegole().getNumeroGiocatori();
        for (int i = 1; i <= numeroGiocatori; i++) {
            Giocatore giocatore = new Giocatore("P" + i);
            giocatori.add(giocatore);
            System.out.println("Giocatore " + i + " aggiunto");
        }
    }

    private void turno(Giocatore giocatore) {

        if (giocatore.getTurniDaSaltare() > 0) {
            if (giocatore.getDivietoDiSosta() == 0) {
                giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare() - 1);
                System.out.println("\nIl giocatore " + giocatore.getNome() + " salta il turno, " + giocatore.getTurniDaSaltare()
                        + " turni rimasti da saltare");
                return;
            } else {
                giocatore.setDivietoDiSosta(giocatore.getDivietoDiSosta() - 1);
                giocatore.setTurniDaSaltare(0);
                System.out.println("\nIl giocatore " + giocatore.getNome() + " usa carta divieto di sosta e può tirare i dadi");
            }
        }

        int passi;
        Random random = new Random();

        do {
            System.out.println("\nTurno giocatore " + giocatore.getNome());
            if (tabellone.getRegole().isUnDadoAllaFine() && giocatore.getCasella().getNumeroCasella() > traguardo.getNumeroCasella() - 6) {
                passi = random.nextInt(6) + 1;
                System.out.println("Il giocatore " + giocatore.getNome() + " tira un solo dado per la regola lancio di un solo dado");
                System.out.println("dado 1: " + passi + "\n");
                muoviGiocatore(giocatore, passi, traguardo.getNumeroCasella(), dadoStrategy);
            } else {
                passi = dadoStrategy.lancia();
                muoviGiocatore(giocatore, passi, traguardo.getNumeroCasella(), dadoStrategy);
            }
        } while (passi == 12 && tabellone.getRegole().isDoppioSei());
        verificaVittoria(giocatore);

        //comunica fine del turno TODO

    }

    private void verificaVittoria(Giocatore giocatore) {
        if (giocatore.getCasella().equals(traguardo)) {
            this.finita = true;
            this.vincitore = giocatore;
            //comunica la vittoria di giocatore TODO
        }
    }

    private void muoviGiocatore(Giocatore giocatore, int passi, int traguardo, DadoStrategy dadoStrategy) {
        giocatore.muovi(passi, traguardo, tabellone);
        giocatore.getCasella().effetto(giocatore, dadoStrategy, traguardo, passi, tabellone);

    }

    public void avviaPartita() {
        inizializzaGiocatori();
        Giocatore giocatore;
        Scanner scanner = new Scanner(System.in);

        while (!finita) {
            giocatore = (Giocatore) giocatoreIterator.next();
            if (!automatica) {
                System.out.println("Premi invio per avanzare al turno successivo...");
                scanner.nextLine();
            }
            turno(giocatore);
        }
        //COMUNICA WIN
        System.out.println("Vittoria del giocatore " + vincitore.getNome());
        scanner.close();
    }
}
