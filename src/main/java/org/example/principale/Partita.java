package org.example.principale;

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

public enum Partita {
    ISTANZA;

    private Tabellone tabellone;
    private List<Giocatore> giocatori;
    private GiocatoreIterator giocatoreIterator;
    private Casella traguardo;
    private DadoStrategy dadoStrategy;
    private boolean finita;
    private boolean automatica;
    private static JTextArea areaTestoTurni;

    // Metodo per inizializzare i campi della partita
    public void init(Tabellone tabellone, boolean automatica, JTextArea areaTestoTurni) {
        if (this.tabellone == null) {  // Assicura che l'inizializzazione avvenga una sola volta
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
        giocatore.getStato().eseguiTurno(giocatore,tabellone, traguardo, dadoStrategy);
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

    public static void setAreaTestoTurni(JTextArea areaTestoTurni) {
        Partita.areaTestoTurni = areaTestoTurni;
    }
}
