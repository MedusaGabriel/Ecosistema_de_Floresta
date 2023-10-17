
package Floresta;

import java.util.Random;

public class Arvore {
    private String estagio;
    private int tempoCrescimentoMuda; // Tempo em ciclos para crescer a partir de "muda" para "jovem"
    private int tempoCrescimentoJovem; // Tempo em ciclos para crescer a partir de "jovem" para "adulta"
    private int tempoCrescimentoAdulta; // Tempo em ciclos para dar vida a novas mudas
    private int tempoVidaAdulta; // Tempo em ciclos para permanecer no estágio "adulta"
    private double chanceNascimento; // Chance de dar vida a novas mudas
    private int ciclosDecorridos;
    private int mudasNascidas;
    private boolean senil; // Variável para rastrear o estágio "Senil"

    /**
     * 
     */
    public Arvore() {
        estagio = "muda";
        tempoCrescimentoMuda = 1;
        tempoCrescimentoJovem = 2;
        tempoCrescimentoAdulta = 5;
        tempoVidaAdulta = 6;
        chanceNascimento = 0.8; // 80% de chance de dar vida a novas mudas
        ciclosDecorridos = 0;
        mudasNascidas = 0;
        senil = false; // Inicialmente, as árvores não estão no estágio "Senil"
    }

    public void executarCiclos(int numeroDeCiclos) {
        for (int i = 0; i < numeroDeCiclos; i++) {
            envelhecer();
        }
    }

    public void envelhecer() {
        ciclosDecorridos++;

        if (estagio.equals("muda") && ciclosDecorridos >= tempoCrescimentoMuda) {
            estagio = "jovem";
            ciclosDecorridos = 0;
        } else if (estagio.equals("jovem") && ciclosDecorridos >= tempoCrescimentoJovem) {
            estagio = "adulta";
            ciclosDecorridos = 0;
        } else if (estagio.equals("adulta")) {
            if (ciclosDecorridos == tempoCrescimentoAdulta) {
                Random rand = new Random();
                if (rand.nextDouble() <= chanceNascimento) {
                    System.out.println("Novas mudas estão nascendo!");
                    mudasNascidas++;
                }
            }
            if (ciclosDecorridos >= tempoVidaAdulta) {
                estagio = "senil";
                senil = true; 
                tempoVidaAdulta = 0; 
                ciclosDecorridos = 0;
            }
        } else if (estagio.equals("senil")) {
            if (mudasNascidas == 0) { // Se não houver novas mudas, a árvore morre
                estagio = "morta";
            } else {
                mudasNascidas--;
            }
        }
    }

    public String getEstagio() {
        return estagio;
    }

    public boolean estaSenil() {
        return senil;
    }
}
