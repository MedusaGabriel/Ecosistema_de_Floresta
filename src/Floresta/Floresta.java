package Floresta;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

public class Floresta {
    private HashSet<Arvore> arvores;
    private HashSet<Arbusto> arbustos;

    public Floresta() {
        arvores = new HashSet<>();
        arbustos = new HashSet<>();
        // Adicione uma única instância de Arvore e Arbusto à floresta
        Arvore arvore = new Arvore();
        Arbusto arbusto = new Arbusto();
        arvores.add(arvore);
        arbustos.add(arbusto);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                atualizarFloresta();
            }
        }, 0, 1 * 1000); // Atualize a cada 1 segundo (tempo em milissegundos)
    }

    public void atualizarFloresta() {
        System.out.println("----------- Floresta ----------");
        for (Arvore arvore : arvores) {
            arvore.envelhecer(); // Chame o método envelhecer para a árvore
            System.out.println("Árvores: Estágio = " + arvore.getEstagio());
        }
        for (Arbusto arbusto : arbustos) {
            arbusto.envelhecer();
            System.out.println("Arbusto: Frutinhas disponíveis = " + arbusto.getFrutinhasDisponiveis());
        }
        System.out.println("---------------------------------");
    }

    public static void main(String[] args) {
        new Floresta();
    }
}
