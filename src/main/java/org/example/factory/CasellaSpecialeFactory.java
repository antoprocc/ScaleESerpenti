package org.example.factory;

import org.example.caselle.*;

public class CasellaSpecialeFactory implements CasellaFactory {

    @Override
    public Casella createCasella(int numeroCasella, String tipo, int destinazione) {
        switch (tipo.toLowerCase()) {
            case "serpente":
                return new CasellaSerpente(numeroCasella, destinazione);
            case "scala":
                return new CasellaScala(numeroCasella, destinazione);
            case "panchina":
                return new CasellaPanchina(numeroCasella);
            case "locanda":
                return new CasellaLocanda(numeroCasella);
            case "dadi":
                return new CasellaDadi(numeroCasella);
            case "molla":
                return new CasellaMolla(numeroCasella);
            case "pesca":
                return new CasellaPesca(numeroCasella);
            default:
                throw new IllegalArgumentException("Tipo di casella sconosciuto: " + tipo);
        }
    }
}
