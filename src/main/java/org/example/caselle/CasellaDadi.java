package org.example.caselle;

public class CasellaDadi extends Casella{

    private String tipo;

    public CasellaDadi(int r, int c){
        super();
        tipo= "dadi";
    }

    public String getTipo() {
        return tipo;
    }
}

