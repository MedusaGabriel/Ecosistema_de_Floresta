package Floresta;

public class Lobo {
    private int fome;
    private int vida;
    
    public Lobo(){
        vida = 10;
        fome = 10;
    }
    public void viver() {
        if (vida > 0) {
            fome--;
            if (fome < 0) {
                vida--;
            }
            if (vida <= 0) {
               // System.out.println("O lobo morreu de fome.");
            }
        }
    }
    public void comerCoelho(Coelho coelho){
        if(coelho.getVida() > 0){
            int coelhoComido = coelho.comer();
            vida += coelhoComido;
        }
    }

    public String getStatus() {
        String status = "Estado: ";
        status += (vida > 0) ? "Vivo" : "Morto";
        status += "\nVida Do Lobo: " + vida;
        status += "\nFome: " + fome;
        return status;
    }

}
