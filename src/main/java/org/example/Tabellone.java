package org.example;

import org.example.caselle.Casella;
import org.example.caselle.CasellaBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Tabellone {

    private final int righe;
    private final int colonne;
    private Map<Casella, Integer> tabellone;


    public Tabellone(int righe, int colonne){
        this.righe=righe;
        this.colonne=colonne;
        tabellone = new HashMap<>();
    }

    private void inizializzaTabellone(Regole regole){

        for (int i=1;i<=righe*colonne;i++)
            tabellone.put(new CasellaBase(i),i);


        //decido di inserire una scala e un serpente in proporzione 1/8 ciascuno rispetto al numero di caselle (come nella
        //maggior parte dei tabelloni)
        int nSerpenti = righe*colonne*8/100;
        int nScale = righe*colonne*8/100;

        //inserire scale e serpenti
        //TODO

        aggiungiCaselleSpeciali(regole);
    }


    private void aggiungiCaselleSpeciali(Regole regole){}




}
