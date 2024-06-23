package org.example.caselle;

public class CasellaSeprente extends Casella {

    private int destinazione;
    private String tipo;

    public CasellaSeprente(int numeroCasella, int destinazione){
        super();
        tipo= "serpente";
        if( destinazione>=numeroCasella)
            throw new RuntimeException("il serpente nella casella "+numeroCasella+" non è valido");
        this.destinazione=destinazione;
    }

    public String getTipo() {
        return tipo;
    }
}
