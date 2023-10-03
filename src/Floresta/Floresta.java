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
        // Agende a atualização a cada 10 segundos
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                atualizarFloresta();
            }
        }, 0, 10 * 1000); // Atualize a cada 10 segundos (tempo em milissegundos)
    }

    public void atualizarFloresta() {
        System.out.println("----- Situação da Floresta -----");
        for (Arvore arvore : arvores) {
            arvore.envelhecer();
            System.out.println("Árvore: Estágio = " + arvore.getEstagio());
        }
        for (Arbusto arbusto : arbustos) {
            System.out.println("Arbusto: Frutinhas disponíveis = " + arbusto.getFrutinhasDisponiveis());
           // int novasFrutinhas = arbusto.comerFrutinhas(1); // Consuma frutinhas após exibir
            // Se novas frutinhas forem consumidas, exibir a mensagem
            //if (novasFrutinhas > 0) {
               // System.out.println(novasFrutinhas + " frutinhas foram consumidas.");
           // }
        }
        System.out.println("---------------------------------");
    }


    public static void main(String[] args) {
        Floresta floresta = new Floresta();
    }
}
