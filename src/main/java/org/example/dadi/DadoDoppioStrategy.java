package org.example.dadi;

import org.example.Partita;

public class DadoDoppioStrategy implements DadoStrategy{
    @Override
    public int lancia() {
        int ret1 = random.nextInt(6) + 1;
        int ret2 = random.nextInt(6) + 1;
        Partita.appendiTestoTurni("Dado 1: "+ret1+"\nDado 2: "+ret2);
        return ret1+ret2;

    }
}
