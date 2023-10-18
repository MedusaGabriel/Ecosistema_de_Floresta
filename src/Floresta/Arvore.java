package Floresta;

import java.util.Random;

public class Arvore {
    private String estagio;
    private int ciclosDecorridos;
    private int mudasNascidas;

    public Arvore() {
        estagio = "muda";
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

        if (estagio.equals("muda") && ciclosDecorridos >= 2) {
            estagio = "jovem";
            ciclosDecorridos = 0;
        } else if (estagio.equals("jovem") && ciclosDecorridos >= 4) {
            estagio = "adulta";
            ciclosDecorridos = 0;
        } else if (estagio.equals("adulta") && ciclosDecorridos >= 12) {
            Random rand = new Random();
            if (rand.nextDouble() <= 0.8) {
                System.out.println("Novas mudas estão nascendo!");
                mudasNascidas++;
            }
            if (ciclosDecorridos >= 20) {
                estagio = "senil";
                ciclosDecorridos = 0;
            }
        } else if (estagio.equals("senil")) {
            if (mudasNascidas == 0) {
                estagio = "morta";
            } else {
                mudasNascidas--;
            }
        }
    }

    public String getEstagio() {
        return estagio;
    }

}