package org.example.caselle;

public class CasellaScala extends Casella {

    private int destinazione;
    private String tipo;

    public CasellaScala(int numeroCasella, int destinazione){
        super();
        tipo= "scala";
        if( destinazione<=numeroCasella)
            throw new RuntimeException("la scala nella casella "+numeroCasella+" non è valida");
        this.destinazione=destinazione;

    }

    public String getTipo() {
        return tipo;
    }
}
