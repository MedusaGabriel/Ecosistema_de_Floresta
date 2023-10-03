package Floresta;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Arbusto {
    private int idade;
    private String frutinhas;
    private int tempoCrescer;
    private int frutinhasDisponiveis;
    private Timer timer;
    private int maxFrutinhas = 10; // Limite máximo de frutinhas

    public Arbusto() {
        idade = 0;
        frutinhas = "alimento";
        frutinhasDisponiveis = 0;
        tempoCrescer = 30; // Tempo em segundos para crescer novas frutinhas
        timer = new Timer();
        scheduleFrutinhas();
    }

    // Método para consumir frutinhas
    public int comerFrutinhas(int quantidade) {
        int frutinhasConsumidas = 0;
        if (frutinhasDisponiveis >= quantidade) {
            frutinhasDisponiveis -= quantidade;
            frutinhasConsumidas = 10 * quantidade; // Ganha 10 de alimento por frutinha
        } else {
            // Se não há frutinhas suficientes, apenas consuma o que está disponível
            frutinhasConsumidas = 10 * frutinhasDisponiveis;
            frutinhasDisponiveis = 0;
        }
        return frutinhasConsumidas;
    }

    public int getFrutinhasDisponiveis() {
        return frutinhasDisponiveis;
    }

    // Agendando a geração de novas frutinhas a cada 30 segundos
    private void scheduleFrutinhas() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (frutinhasDisponiveis < maxFrutinhas) {
                    int novasFrutinhas = new Random().nextInt(1) + 1;
                    if (frutinhasDisponiveis + novasFrutinhas <= maxFrutinhas) {
                        frutinhasDisponiveis += novasFrutinhas;
                    } else {
                        frutinhasDisponiveis = maxFrutinhas;
                    }
                    System.out.println(novasFrutinhas + " frutinhas nasceram!");
                }
            }
        }, 0, tempoCrescer * 1000);
    }
}
