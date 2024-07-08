package org.example.factory;

import org.example.caselle.Casella;

public interface CasellaFactory {
    Casella createCasella(int numeroCasella, String tipo, int destinazione);
}
