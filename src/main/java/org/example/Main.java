package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("inizio");

        Regole regole = new Regole(10,10,2,4,2,2,2,
                2,2,2,true,false);

        System.out.println("regole impostate");


        Tabellone tabellone=new Tabellone(regole);

        System.out.println("tabellone creato");


        Partita partita=Partita.getInstance(tabellone);

        System.out.println("partita creata");

        partita.avviaPartita();

    }
}