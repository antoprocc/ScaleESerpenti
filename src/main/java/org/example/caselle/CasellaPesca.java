package org.example.caselle;

public class CasellaPesca extends Casella{

    private String tipo;

    public CasellaPesca(int numeroCasella){
        super();
        tipo= "sosta";
    }

    public String getTipo() {
        return tipo;
    }
}

