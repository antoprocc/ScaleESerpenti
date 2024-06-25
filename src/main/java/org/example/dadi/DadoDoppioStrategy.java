package org.example.dadi;

public class DadoDoppioStrategy implements DadoStrategy{
    @Override
    public int lancia() {

        int ret1 = random.nextInt(6) + 1;
        int ret2 = random.nextInt(6) + 1;
        //stampa il valore del dado con "dado 1: "+ret1 + "dado 2: "+ret2
        return ret1+ret2;
    }
}
