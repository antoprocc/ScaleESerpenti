package org.example;

import org.example.caselle.Casella;
import org.example.caselle.CasellaBase;
import org.example.dadi.DadoDoppioStrategy;
import org.example.dadi.DadoSingoloStrategy;
import org.example.dadi.DadoStrategy;
import org.example.iterator.GiocatoreIterator;
import org.example.iterator.GiocatoreListIterator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private JTextArea turnInfoTextArea;

    private Partita(Tabellone tabellone, boolean automatica, JTextArea turnInfoTextArea) {
        this.tabellone = tabellone;
        this.giocatori = new ArrayList<>();
        this.giocatoreIterator = new GiocatoreListIterator(giocatori);
        this.finita = false;
        this.vincitore = null;
        this.automatica = automatica;
        this.turnInfoTextArea = turnInfoTextArea;
        traguardo = new CasellaBase(tabellone.getRegole().getRighe() * tabellone.getRegole().getColonne());

        if (tabellone.getRegole().getNumeroDadi() == 1)
            this.dadoStrategy = new DadoSingoloStrategy();
        else if (tabellone.getRegole().getNumeroDadi() == 2)
            this.dadoStrategy = new DadoDoppioStrategy();
        else
            appendToTextArea("Numero dadi non corretto\n");
    }

    public static synchronized Partita getInstance(Tabellone tabellone, boolean automatica, JTextArea turnInfoTextArea) {
        if (istanza == null)
            istanza = new Partita(tabellone, automatica, turnInfoTextArea);
        return istanza;
    }

    private void appendToTextArea(String message) {
        SwingUtilities.invokeLater(() -> turnInfoTextArea.append(message + "\n"));
    }

    private void inizializzaGiocatori() {
        int numeroGiocatori = this.tabellone.getRegole().getNumeroGiocatori();
        for (int i = 1; i <= numeroGiocatori; i++) {
            Giocatore giocatore = new Giocatore("P" + i);
            giocatori.add(giocatore);
            appendToTextArea("Giocatore " + i + " aggiunto");
        }
    }

    private void turno(Giocatore giocatore) {

        if (giocatore.getTurniDaSaltare() > 0) {
            if (giocatore.getDivietoDiSosta() == 0) {
                giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare() - 1);
                appendToTextArea("\nIl giocatore " + giocatore.getNome() + " salta il turno, " + giocatore.getTurniDaSaltare()
                        + " turni rimasti da saltare");
                return;
            } else {
                giocatore.setDivietoDiSosta(giocatore.getDivietoDiSosta() - 1);
                giocatore.setTurniDaSaltare(0);
                appendToTextArea("\nIl giocatore " + giocatore.getNome() + " usa carta divieto di sosta e può tirare i dadi");
            }
        }

        int passi;
        Random random = new Random();

        do {
            appendToTextArea("\nTurno giocatore " + giocatore.getNome());
            if (tabellone.getRegole().isUnDadoAllaFine() && giocatore.getCasella().getNumeroCasella() > traguardo.getNumeroCasella() - 6) {
                passi = random.nextInt(6) + 1;
                appendToTextArea("Il giocatore " + giocatore.getNome() + " tira un solo dado per la regola lancio di un solo dado");
                appendToTextArea("dado 1: " + passi + "\n");
                muoviGiocatore(giocatore, passi, traguardo.getNumeroCasella(), dadoStrategy);
            } else {
                passi = dadoStrategy.lancia();
                muoviGiocatore(giocatore, passi, traguardo.getNumeroCasella(), dadoStrategy);
            }
        } while (passi == 12 && tabellone.getRegole().isDoppioSei());
        verificaVittoria(giocatore);
    }

    private void verificaVittoria(Giocatore giocatore) {
        if (giocatore.getCasella().equals(traguardo)) {
            this.finita = true;
            this.vincitore = giocatore;
            appendToTextArea("Vittoria del giocatore " + vincitore.getNome());
        }
    }

    private void muoviGiocatore(Giocatore giocatore, int passi, int traguardo, DadoStrategy dadoStrategy) {
        giocatore.muovi(passi, traguardo, tabellone);
        giocatore.getCasella().effetto(giocatore, dadoStrategy, traguardo, passi, tabellone);
    }

    public void avanzaTurnoManuale() {
        if (!finita) {
            Giocatore giocatore = (Giocatore) giocatoreIterator.next();
            turno(giocatore);
        }
    }

    public void avviaPartita() {
        inizializzaGiocatori();
        if (automatica) {
            while (!finita) {
                Giocatore giocatore = (Giocatore) giocatoreIterator.next();
                turno(giocatore);
            }
            appendToTextArea("Vittoria del giocatore " + vincitore.getNome());
        }
    }
}
