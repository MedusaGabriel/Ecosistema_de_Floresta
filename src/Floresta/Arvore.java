package Floresta;

import java.util.Random;

public class Arvore {
    private int idade;
    private String estagio;
    private int tempoFlorescimento; // Tempo em minutos de florescimento
    private int tempoDecorrido; // Tempo decorrido durante o florescimento
    private boolean floresceu; // Indica se a árvore floresceu

    public Arvore() {
        idade = 0;
        estagio = "muda";
        tempoFlorescimento = 10;
        tempoDecorrido = 0;
        floresceu = false;
    }

    public void envelhecer() {
        if (estagio.equals("adulta") && !floresceu) {
            // A árvore adulta entrou no estágio de florescimento
            tempoDecorrido++;
            if (tempoDecorrido >= tempoFlorescimento) {
                floresceu = true;
                tempoDecorrido = 0;
            } else {
                // 10% de chance de fazer nascer uma nova árvore a cada minuto de florescimento
                Random rand = new Random();
                if (rand.nextDouble() <= 0.1) {
                    // Nasceu uma nova árvore infantil
                    System.out.println("Uma nova mudar árvore nasceu!");
                }
            }
        }

        idade += 1; // A cada 1 minuto
        if (idade >= 10) {
            estagio = "adulta";
        } else if (idade >= 5) {
            estagio = "jovem";
        }
    }

    public String getEstagio() {
        if (floresceu) {
            return "Florescimento";
        } else {
            return estagio;
        }
    }
}
