package org.example.caselle;

public class CasellaSeprente extends Casella {

    private CasellaBase destinazione;
    private String tipo;

    public CasellaSeprente(int r, int c){
        super();
        tipo= "serpente";
        //destinazione=new CasellaBase(r,c); #da sistemare
    }

    public String getTipo() {
        return tipo;
    }
}
