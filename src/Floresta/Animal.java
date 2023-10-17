package Floresta;

public class Animal {
    private int vida;
    private static int AnimaisVivos = 0;

    public Animal(int vida) {
        this.vida = vida;
        AnimaisVivos++;
    }

    public void viver() {
        if (vida > 0) {
            vida--;
        }
    }
    public boolean estaVivo() {
        return vida > 0;
    }

    public void morrer() {
        vida = 0;
        AnimaisVivos--;
    }

    public int getVida() {
        return vida;
    }
    public boolean estaCacando() {
        return false; 
    }
    public static int getAnimaisVivos() {
        return AnimaisVivos;
    }
}
