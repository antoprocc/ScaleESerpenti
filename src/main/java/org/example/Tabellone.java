package org.example;

import org.example.caselle.Casella;
import org.example.caselle.CasellaBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Tabellone {


    private Map<Casella, Integer> tabellone;
    private Regole regole;


    public Tabellone(Regole regole){

        tabellone = new HashMap<>();
        this.regole=regole;
    }

    private void inizializzaTabellone(Regole regole){

        int righe = regole.getRighe();
        int colonne = regole.getColonne();

        for (int i=1;i<=righe*colonne;i++)
            tabellone.put(new CasellaBase(i),i);


        //decido di inserire una scala e un serpente in proporzione 1/8 ciascuno rispetto al numero di caselle (come nella
        //maggior parte dei tabelloni)
        int nSerpenti = righe*colonne*8/100; //uguale per le scale

        aggiungiScaleESerpenti(nSerpenti);


        aggiungiCaselleSpeciali(regole);
    }


    private void aggiungiScaleESerpenti(int nSerpenti) {
        //TODO
    }


    private void aggiungiCaselleSpeciali(Regole regole){
        //TODO
    }



    public void effettoCasella(Casella casella) {
        //TODO
    }


    public Regole getRegole() {
        return regole;
    }
}
