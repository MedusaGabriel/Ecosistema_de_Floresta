package Floresta;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Lobo extends Animal {
    private int fome;
    private Timer fomeTimer;
    private Timer cacarTimer;
    private boolean cacando;
    private Random random;

    public Lobo() {
        super(10);
        fome = 20;
        cacando = false;
        random = new Random();

        fomeTimer = new Timer();
        fomeTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                perderFome();
            }
        }, 1000, 1000); // Perder 1 de fome a cada segundo (tempo em milissegundos)

        cacarTimer = new Timer();
        cacarTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tentarCacar();
            }
        }, 0, 3 * 1000); // Tentar caçar a cada 3 segundos (tempo em milissegundos)
    }

    public void viver() {
        super.viver();
        if (fome <= 0) {
            super.morrer();
        }
    }

    public void comerCoelho(Coelho coelho) {
        if (coelho.estaVivo()) {
            int coelhoComido = coelho.comer();
            fome += coelhoComido;
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
        status += "\nFome do Lobo: " + fome;
        return status;
    }

    public void tentarCacar() {
        if (random.nextDouble() < 0.5) { // 50% de chance de tentar caçar
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
            fome += 3; // Ajuste o valor conforme necessário
            pararCacar(); // Após a tentativa de caça, o Lobo para de caçar
        }
    }
}
