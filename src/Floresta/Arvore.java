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

    /**
     * 
     */
    public Arvore() {
        estagio = "muda";
        tempoCrescimentoMuda = 15;
        tempoCrescimentoJovem = 15;
        tempoCrescimentoAdulta = 30;
        tempoVidaAdulta = 30;
        chanceNascimento = 0.8; // 80% de chance de dar vida a novas mudas
        ciclosDecorridos = 0;
        mudasNascidas = 0;
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
                tempoVidaAdulta = 0; // Resete o tempo na vida adulta para evitar novas mudas
                ciclosDecorridos = 0;
            }
        } else if (estagio.equals("senil")) {
            if (mudasNascidas == 0) {
                // Não há mais mudas nascendo, a árvore atingiu o final de sua vida
                System.out.println("A árvore chegou ao fim de sua vida.");
            }
        }
    }

    public String getEstagio() {
        return estagio;
    }
}
