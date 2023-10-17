package Floresta;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Pantera extends Animal{
    private int fome;
    private Timer fomeTimer;
    private Timer cacarTimer;
    private boolean cacando;
    private Random random; 

    public Pantera(){
        super(10);
        fome = 70;
        cacando = false;
        random = new Random();
        fomeTimer = new Timer();
        fomeTimer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                perderFome();
            }
        }, 1000, 1000); // Perder 3 de fome a cada segundo (tempo em milissegundos)

        cacarTimer = new Timer();
        cacarTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tentarCacar();
            }
        }, 0, 2 * 1000); // Tentar caçar a cada 2 segundos (tempo em milissegundos)

    }

    public void viver() {
        super.viver();
        if (fome <= 0) {
            super.morrer();
        }
    }

    public void comerCervo(Cervo cervo) {
        if (cervo.estaVivo()) {
            int cervoComido = cervo.comer();
            fome += cervoComido;
        }
    }

    public void perderFome() {
        if (estaVivo() && fome > 0) {
            fome--;
        }
        if (fome <= 0) {
            super.morrer();
        }
    }

    public String getStatus() {
        String status = "Estado: ";
        status += (estaVivo()) ? "Vivo" : "Morto";
        status += "\nFome da Pantera: " + fome;
        return status;
    }

    public void tentarCacar() {
        if (random.nextDouble() < 0.3) { // 30% de chance de tentar caçar
            cacando = true;
        }
    }

    public boolean estaCacando() {
        return cacando;
    }

    public void pararCacar() {
        cacando = false;
    }

    public void cacarCervo(Cervo cervo) {
        if (cacando && cervo.estaVivo()) {
            cervo.serCacado();
            fome += 3;
            pararCacar();
        }
    }

}
