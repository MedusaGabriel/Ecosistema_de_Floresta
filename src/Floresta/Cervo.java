package Floresta;

public class Cervo extends Animal{
    private int energia;
    
    public Cervo(){
        super(10);
        energia = 100;
    }
    public void viver(){
        super.viver();
    }

    public String getStatus(){
        String status = "Estado: ";
        status += (estaVivo()) ? "Vivo" : "Morto";
        status += "\nEnergia do Cervo: " + energia;
        return status;
    }
    public int comer(){
        if (estaVivo()){
            int comida = energia;
            energia = 0;
            return comida;
        }
        return 0;
    }   
    public void serCacado(){
        if (estaVivo()){
            energia -= 10;
        }
    }

    public void fugir(Pantera pantera){
        if (estaVivo() && pantera.estaCacando()){
            energia -= 5;
            if (energia <= 0){
                morrer();
            }
        }
    }
    
}
