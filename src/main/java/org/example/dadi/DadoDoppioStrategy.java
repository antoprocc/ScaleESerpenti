package org.example.dadi;

public class DadoDoppioStrategy implements DadoStrategy{
    @Override
    public int lancia() {
        int ret1 = random.nextInt(6) + 1;
        int ret2 = random.nextInt(6) + 1;
        return ret1+ret2;
    }

    public int[] lanciaDoppi() {
        int ret1 = random.nextInt(6) + 1;
        int ret2 = random.nextInt(6) + 1;
        return new int[]{ret1, ret2};
    }
}
