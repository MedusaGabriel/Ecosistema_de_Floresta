package Floresta;

public class Animal {
    private int vida;

    public Animal(int vida) {
        this.vida = vida;
    }

    public void viver() {
        if (estaVivo()) {
            vida--;
        }
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void morrer() {
        vida = 0;
    }

    public int getVida() {
        return vida;
    }

    public boolean estaCacando() {
        return false;
    }
}
