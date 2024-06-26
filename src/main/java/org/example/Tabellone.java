package org.example;

import org.example.caselle.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Tabellone {


    private final Map<Casella, Boolean> tabellone;
    private final Regole regole;


    public Tabellone(Regole regole){

        tabellone = new HashMap<>();
        this.regole=regole;
    }

    private void inizializzaTabellone(Regole regole){

        int righe = regole.getRighe();
        int colonne = regole.getColonne();

        for (int i=1;i<=righe*colonne;i++)
            tabellone.put(new CasellaBase(i),true);


        //decido di inserire una scala e un serpente in proporzione 1/8 ciascuno rispetto al numero di caselle (come nella
        //maggior parte dei tabelloni)
        int nSerpenti = righe*colonne*8/100; //uguale per le scale

        aggiungiScaleESerpenti(nSerpenti);


        aggiungiCaselleSpeciali(regole);
    }


    private void aggiungiScaleESerpenti(int nSerpenti) {

        Random random = new Random();
        int righe = regole.getRighe();
        int colonne = regole.getColonne();
        int numeroCaselle = righe * colonne;

        //SERPENTI
        for (int i = 0; i < nSerpenti; i++) {
            int bocca, coda;
            do {
                bocca = random.nextInt(2, numeroCaselle);
                coda = random.nextInt(1, bocca);
            } while (!tabellone.get(bocca) || !tabellone.get(coda));

            tabellone.put(new CasellaSeprente(bocca, coda), false);
            tabellone.put(new CasellaBase(coda), false);

        }

        //SCALE
        for (int i = 0; i < nSerpenti; i++) {
            int base, cima;
            do {
                base = random.nextInt(1, numeroCaselle - 1);
                cima = random.nextInt(base + 1, numeroCaselle);
            } while (!tabellone.get(base) || !tabellone.get(cima));

            tabellone.put(new CasellaScala(base, cima), false);
            tabellone.put(new CasellaBase(cima), false);
        }
    }


    private void aggiungiCaselleSpeciali(Regole regole){
        Random random = new Random();
        int righe = regole.getRighe();
        int colonne = regole.getColonne();
        int numeroCaselle = righe * colonne;
        int casella;

        //panchina
        for(int i = 0; i< regole.getCasellaPanchina(); i++){
            do{
                casella = random.nextInt(2,numeroCaselle);
            }while(!tabellone.get(casella)); //non specializzabile
            tabellone.put((new CasellaPanchina(casella)),false);
        }

        //locanda
        for(int i = 0; i< regole.getCasellaLocanda(); i++){
            do{
                casella = random.nextInt(2,numeroCaselle);
            }while(!tabellone.get(casella)); //non specializzabile
            tabellone.put((new CasellaLocanda(casella)),false);
        }

        //dadi
        for(int i = 0; i< regole.getCasellaDadi(); i++){
            do{
                casella = random.nextInt(2,numeroCaselle);
            }while(!tabellone.get(casella)); //non specializzabile
            tabellone.put((new CasellaDadi(casella)),false);
        }

        //molla
        for(int i = 0; i< regole.getCasellaMolla(); i++){
            do{
                casella = random.nextInt(2,numeroCaselle);
            }while(!tabellone.get(casella)); //non specializzabile
            tabellone.put((new CasellaMolla(casella)),false);
        }

        //pesca
        for(int i=0;i< regole.getCasellaPesca();i++){
            do{
                casella = random.nextInt(2,numeroCaselle);
            }while(!tabellone.get(casella)); //non specializzabile
            tabellone.put((new CasellaPesca(casella)),false);
        }

        //DivietoDiSosta
        for(int i=0;i< regole.getCasellaDivietoDiSosta();i++){
            do{
                casella = random.nextInt(2,numeroCaselle);
            }while(!tabellone.get(casella)); //non specializzabile
            tabellone.put((new CasellaDivietoDiSosta(casella)),false);
        }
    }




    public Regole getRegole() {
        return regole;
    }
}
