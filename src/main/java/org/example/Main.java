package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("inizio");

        Regole regole = new Regole(6,8,1,2,2,2,2,
                2,2,2,true,true);

        System.out.println("regole impostate");


        Tabellone tabellone=new Tabellone(regole);

        System.out.println("tabellone creato");


        Partita partita=Partita.getInstance(tabellone,false);

        System.out.println("partita creata");

        partita.avviaPartita();


    }
}