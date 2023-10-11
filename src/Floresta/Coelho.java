package Floresta;

public class Coelho extends Animal {
    private int energia;

    public Coelho() {
        super(50);
        energia = 20;
    }

    public void viver() {
        super.viver();
    }

    public void comerFrutinhas(Arbusto arbusto) {
        if (arbusto.getFrutinhasDisponiveis() > 0) {
            int frutinhasConsumidas = arbusto.comerFrutinhas(1);
            energia += frutinhasConsumidas;
        }
    }

    public String getStatus() {
        String status = "Estado: ";
        status += (estaVivo()) ? "Vivo" : "Morto";
        status += "\nEnergia do Coelho: " + energia;
        return status;
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
        // Reduz a energia quando o coelho é caçado pelo lobo
        if (estaVivo()) {
            energia -= 10; // Ajuste o valor conforme necessário
        }
    }

    public void fugir(Lobo lobo) {
        if (estaVivo() && lobo.estaCacando()) {
            energia -= 5; // Ajuste o valor conforme necessário
            if (energia <= 0) {
                morrer();
            }
        }
    }
}
