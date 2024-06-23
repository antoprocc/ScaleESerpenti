package org.example.caselle;

public class CasellaSosta extends Casella{

    private String tipo;

    public CasellaSosta(int numeroCasella){
        super();
        tipo= "sosta";
    }

    public String getTipo() {
        return tipo;
    }
}
