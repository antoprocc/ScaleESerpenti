package org.example;

import org.example.caselle.Casella;
import org.example.caselle.CasellaBase;
import org.example.dadi.DadoDoppioStrategy;
import org.example.dadi.DadoSingoloStrategy;
import org.example.dadi.DadoStrategy;

import org.example.iterator.GiocatoreIterator;
import org.example.iterator.GiocatoreListIterator;
import org.example.observer.Observer;

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
    private final boolean automatica;
    private static JTextArea areaTestoTurni;

    private Partita(Tabellone tabellone, boolean automatica, JTextArea areaTestoTurni) {
        this.tabellone = tabellone;
        this.giocatori = new ArrayList<>();
        this.giocatoreIterator = new GiocatoreListIterator(giocatori);
        this.finita = false;
        this.automatica = automatica;
        Partita.areaTestoTurni = areaTestoTurni;
        traguardo = new CasellaBase(tabellone.getRegole().getRighe() * tabellone.getRegole().getColonne());

        if (tabellone.getRegole().getNumeroDadi() == 1)
            this.dadoStrategy = new DadoSingoloStrategy();
        else if (tabellone.getRegole().getNumeroDadi() == 2)
            this.dadoStrategy = new DadoDoppioStrategy();
    }

    public static synchronized Partita getInstance(Tabellone tabellone, boolean automatica, JTextArea areaTestoTurni) {
        if (istanza == null)
            istanza = new Partita(tabellone, automatica, areaTestoTurni);
        return istanza;
    }

    private void inizializzaGiocatori() {
        int numeroGiocatori = this.tabellone.getRegole().getNumeroGiocatori();
        for (int i = 1; i <= numeroGiocatori; i++) {
            Giocatore giocatore = new Giocatore("P" + i);
            giocatori.add(giocatore);
            appendiTestoTurni("Giocatore " + i + " aggiunto");
        }
    }

    private void turno(Giocatore giocatore) {
        if (giocatore.getTurniDaSaltare() > 0) {
            if (giocatore.getDivietoDiSosta() == 0) {
                giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare() - 1);
                appendiTestoTurni("\nIl giocatore " + giocatore.getNome() + " salta il turno, " + giocatore.getTurniDaSaltare()
                        + " turni rimasti da saltare");
                return;
            } else {
                giocatore.setDivietoDiSosta(giocatore.getDivietoDiSosta() - 1);
                giocatore.setTurniDaSaltare(0);
                appendiTestoTurni("\nIl giocatore " + giocatore.getNome() + " usa carta divieto di sosta e può tirare i dadi");
            }
        }

        int passi;
        Random random = new Random();

        do {
            appendiTestoTurni("\nTurno giocatore " + giocatore.getNome());
            if (tabellone.getRegole().isUnDadoAllaFine() && giocatore.getCasella().getNumeroCasella() > traguardo.getNumeroCasella() - 6) {
                passi = random.nextInt(6) + 1;
                appendiTestoTurni("Il giocatore " + giocatore.getNome() + " tira un solo dado per la regola lancio di un solo dado");
                appendiTestoTurni("dado 1: " + passi);
                muoviGiocatore(giocatore, passi, traguardo.getNumeroCasella(), dadoStrategy);
            } else {
                passi = dadoStrategy.lancia();
                muoviGiocatore(giocatore, passi, traguardo.getNumeroCasella(), dadoStrategy);
            }
        } while (passi == 12 && tabellone.getRegole().isDoppioSei());
        if(verificaVittoria(giocatore))
            appendiTestoTurni("fine partita");
        else
            appendiTestoTurni("fine turno giocatore "+giocatore.getNome());
    }

    private boolean verificaVittoria(Giocatore giocatore) {
        if (giocatore.getCasella().equals(traguardo)) {
            this.finita = true;
            appendiTestoTurni("VINCE IL GIOCATORE " + giocatore.getNome());
            return true;
        }
        return false;
    }

    private void muoviGiocatore(Giocatore giocatore, int passi, int traguardo, DadoStrategy dadoStrategy) {
        giocatore.muovi(passi, traguardo, tabellone);
        giocatore.getCasella().effetto(giocatore, dadoStrategy, traguardo, passi, tabellone);
    }

    public void avviaPartita() {
        inizializzaGiocatori();
        if (automatica) {
            SwingUtilities.invokeLater(this::turnoAutomatico);
        } else {
            appendiTestoTurni("La partita è pronta. Premi 'Lancia Dadi' per iniziare.");
        }
    }


    private void turnoAutomatico() {
        new Thread(() -> {
            Giocatore giocatore;
            while (!finita) {
                giocatore = (Giocatore) giocatoreIterator.next();
                turno(giocatore);
                try {
                    Thread.sleep(2000); // Aggiungere un piccolo timeout tra i turni per rendere la partita più verosimile
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }


    public void avanzaTurnoManuale() {
        if(finita){
            return;
        }
        Giocatore giocatore = (Giocatore) giocatoreIterator.next();
        turno(giocatore);
    }

    public static void appendiTestoTurni(String testo) {
        SwingUtilities.invokeLater(() -> areaTestoTurni.append(testo + "\n"));
    }

    //registro observer
    public void aggiungiOsservatoreAGiocatori(Observer observer) {
        for (Giocatore giocatore : giocatori) {
            giocatore.addObserver(observer);
        }
    }

    public List<Giocatore> getGiocatori() {
        return giocatori;
    }
}
