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

public final class Partita {

    private static Partita istanza=null;
    private Tabellone tabellone;
    private List<Giocatore> giocatori;
    private GiocatoreIterator giocatoreIterator;
    private Casella traguardo;
    private DadoStrategy dadoStrategy;
    private boolean finita;
    private Giocatore vincitore;

    private Partita(Tabellone tabellone) {
        this.tabellone = tabellone;
        this.giocatori = new ArrayList<>();
        this.giocatoreIterator = new GiocatoreListIterator(giocatori);
        this.finita=false;
        this.vincitore=null;
        traguardo=new CasellaBase(tabellone.getRegole().getRighe()* tabellone.getRegole().getColonne());

        if(tabellone.getRegole().getNumeroDadi()==1)
            this.dadoStrategy=new DadoSingoloStrategy();
        else if (tabellone.getRegole().getNumeroDadi()==2)
            this.dadoStrategy=new DadoDoppioStrategy();
        else
            System.out.println("numero dadi non corretto");
            //notifica errore nel numero dei dadi TODO

    }

    public static synchronized Partita getInstance(Tabellone tabellone){
        if (istanza==null)
            istanza=new Partita(tabellone);
        return istanza;
    }


    private void inizializzaGiocatori() {
        int numeroGiocatori = this.tabellone.getRegole().getNumeroGiocatori();
        for (int i = 1; i <= numeroGiocatori; i++) {
            Giocatore giocatore = new Giocatore("P" + i);
            giocatori.add(giocatore);
            System.out.println("Giocatore "+i+" aggiunto");
        }
    }

    private void turno(Giocatore giocatore) {

        if (giocatore.getTurniDaSaltare() > 0){
            if(giocatore.getDivietoDiSosta()==0){
                giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare() - 1);
                System.out.println("Il giocatore "+giocatore.getNome()+" salta il turno, "+giocatore.getTurniDaSaltare()
                        +" turni rimasti da saltare");
                return;
            }else {
                giocatore.setDivietoDiSosta(giocatore.getDivietoDiSosta() - 1);
                System.out.println("Il giocatore" +giocatore.getNome()+"usa carta divieto di sosta e può tirare i dadi");
            }
        }


        int passi = 0;
        Random random = new Random();

        do {
            if (tabellone.getRegole().isUnDadoAllaFine() &&giocatore.getCasella().getNumeroCasella() > traguardo.getNumeroCasella()-6) {
                passi = random.nextInt(6) + 1;
                muoviGiocatore(giocatore,passi, traguardo.getNumeroCasella(),dadoStrategy);
                System.out.println("Il giocatore tira un solo dado per la regola lancio di un solo dado"+giocatore.getNome());
            } else {
                passi = dadoStrategy.lancia();
                muoviGiocatore(giocatore, passi, traguardo.getNumeroCasella(), dadoStrategy);
            }
        }while (passi == 12 && tabellone.getRegole().isDoppioSei()) ;
        verificaVittoria(giocatore);

        //comunica fine del turno TODO

    }

    private void verificaVittoria(Giocatore giocatore) {
        if(giocatore.getCasella().equals(traguardo)){
            this.finita=true;
            this.vincitore=giocatore;
            //comunica la vittoria di giocatore TODO
        }
    }

    private void muoviGiocatore(Giocatore giocatore, int passi, int traguardo, DadoStrategy dadoStrategy) {
        giocatore.muovi(passi, traguardo, tabellone);
        giocatore.getCasella().effetto(giocatore,dadoStrategy,traguardo,passi, tabellone);

    }

    public void avviaPartita() {
        inizializzaGiocatori();
        Giocatore giocatore = null;
        while (!finita) {
            giocatore = (Giocatore) giocatoreIterator.next();
            turno(giocatore);
        }
        //COMUNICA WIN
        System.out.println("Vittoria del giocatore "+vincitore.getNome());
    }


}
