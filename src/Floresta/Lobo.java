package Floresta;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Lobo extends Animal {
    private int fome;
    private boolean cacando;
    private Random random;

    public Lobo() {
        super(10);
        fome = 45;
        cacando = false;
        random = new Random();
        
        Timer fomeTimer = new Timer();
        fomeTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                perderFome();
            }
        }, 1000, 1000); 

        Timer cacarTimer = new Timer();
        cacarTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tentarCacar();
            }
        }, 0, 2 * 1000); 
    }

    public void viver() {
        super.viver();
        if (fome <= 0) {
            super.morrer();
        }
    }

    public void comerCoelho(Coelho coelho) {
        if (coelho.estaVivo()) {
            fome += coelho.comer();
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
        return "Estado: " + (estaVivo() ? "Vivo" : "Morto") + "\nEnergia do Lobo: " + fome;
    }

    public void tentarCacar() {
        if (random.nextDouble() < 0.3) {
            cacando = true;
        }
    }

    public boolean estaCacando() {
        return cacando;
    }

    public void pararCacar() {
        cacando = false;
    }

    public void cacarCoelho(Coelho coelho) {
        if (cacando && coelho.estaVivo()) {
            coelho.serCacado();
            fome += 3;
            pararCacar();
        }
    }
}
