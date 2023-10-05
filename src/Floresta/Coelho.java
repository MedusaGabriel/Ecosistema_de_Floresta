package Floresta;

public class Coelho {
    //private int energia;
    private int vida;

    public Coelho() {
        //energia = 20;
        vida = 50;
    }

    public void viver() {
    }
    

    public void comerFrutinhas(Arbusto arbusto) {
        if (arbusto.getFrutinhasDisponiveis() > 0) {
            int frutinhasConsumidas = arbusto.comerFrutinhas(1);
            vida += frutinhasConsumidas;
        }
    }


    public String getStatus() {
        String status = "Estado: ";
        status += (vida > 0) ? "Vivo" : "Morto";
        status += "\nVida Do Coelho: " + vida;
        status += "\nEnergia: " + vida;
        return status;
    }

    public int getVida() {
        return 0;
    }

    public int comer() {
        return 0;
    }
}
