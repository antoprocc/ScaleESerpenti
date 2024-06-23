package org.example.caselle;

public class CasellaMolla extends Casella{

    private String tipo;

    public CasellaMolla(int numeroCasella){
        super();
        tipo= "molla";
    }

    public String getTipo() {
        return tipo;
    }
}

