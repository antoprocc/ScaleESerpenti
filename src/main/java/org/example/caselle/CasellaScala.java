package org.example.caselle;

public class CasellaScala extends Casella {

    private CasellaBase destinazione;
    private String tipo;

    public CasellaScala(int r, int c){
        super();
        tipo= "scala";
        //destinazione=new CasellaBase(r,c); #da sistemare

    }

    public String getTipo() {
        return tipo;
    }
}
