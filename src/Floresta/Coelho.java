package Floresta;

public class Coelho extends Animal {
    private int energia;

    public Coelho() {
        super(10);
        energia = 60;
    }

    @Override
    public void viver() {
        super.viver();
    }

    public String getStatus() {
        return "Estado: " + (estaVivo() ? "Vivo" : "Morto") + "\nEnergia do Coelho: " + energia;
    }

    public int comer() {
        if (estaVivo()) {
            int comida = energia;
            energia = 0;
            return comida;
        }
        return 0;
    }

    public void serCacado() {
        if (estaVivo()) {
            energia -= 10;
            if (energia <= 0) {
                morrer();
            }
        }
    }

    public void fugir(Lobo lobo) {
        if (estaVivo() && lobo.estaCacando()) {
            energia -= 5;
            if (energia <= 0) {
                morrer();
            }
        }
    }
}
