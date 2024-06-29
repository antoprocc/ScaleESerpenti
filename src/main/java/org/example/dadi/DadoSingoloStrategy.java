package org.example.dadi;

public class DadoSingoloStrategy implements DadoStrategy {


    @Override
    public int lancia() {

        int ret = random.nextInt(6) + 1;
        //stampa il valore del dado con "dado 1: "+ret
        System.out.println("dado 1: "+ret);
        return ret;

    }


}
