package org.example.caselle;

public class CasellaBase extends Casella {

    private String tipo;

    public CasellaBase(int numeroCasella) {
        super();
        tipo="base";
    }

    public String getTipo() {
        return tipo;
    }
}
