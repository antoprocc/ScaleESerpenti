package org.example.dadi;

import org.example.principale.Partita;

public class DadoSingoloStrategy implements DadoStrategy {


    @Override
    public int lancia() {

        int ret = random.nextInt(6) + 1;
        //stampa il valore del dado con "dado 1: "+ret
        Partita.appendiTestoTurni("dado 1: "+ret);
        return ret;

    }


}
