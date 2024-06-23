package org.example.caselle;

public class CasellaPesca extends Casella{

    private String tipo;

    public CasellaPesca(int r, int c){
        super();
        tipo= "sosta";
    }

    public String getTipo() {
        return tipo;
    }
}

