package Floresta;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

public class Floresta {
    private HashSet<Arvore> arvores;
    private HashSet<Arbusto> arbustos;
    private HashSet<Coelho> coelhos;
    private HashSet<Lobo> lobos;

    public Floresta() {
        System.out.println("Bem-vindo ao Simulador da Floresta!");
        System.out.println("Neste simulador, você poderá acompanhar a vida na floresta, incluindo árvores, arbustos, coelhos e lobos.");
        System.out.println("A cada segundo, a floresta se atualiza, mostrando o desenvolvimento das árvores, a disponibilidade de frutinhas nos arbustos,");
        System.out.println("o estado dos coelhos e lobos, e como eles interagem com o ambiente.");
        System.out.println("Os coelhos buscam comida nos arbustos,e quando tem energia tenta fugir do lobo caso não morrem");
        System.out.println("Os lobos, por sua vez, tentam caçar os coelhos, mas também precisam se alimentar para sobreviver caso não ser alimente dos coelhos morrem.");
        System.out.println("Observe como a vida na floresta evolui à medida que o tempo passa!");


        arvores = new HashSet<>();
        arbustos = new HashSet<>();
        coelhos = new HashSet<>();
        lobos = new HashSet<>();

        Arvore arvore = new Arvore();
        Arbusto arbusto = new Arbusto();
        Coelho coelho = new Coelho();
        Lobo lobo = new Lobo();

        coelhos.add(coelho);    
        arvores.add(arvore);
        arbustos.add(arbusto);
        lobos.add(lobo);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                atualizarFloresta();
            }
        }, 0, 3 * 1000); // Atualize a cada 3 segundo (tempo em milissegundos)

    }

    public void atualizarFloresta() {
        System.out.println("|--------------------- Floresta ------------------------|");
        for (Arvore arvore : arvores) {
            arvore.envelhecer();
            System.out.println("Árvores: Estágio = " + arvore.getEstagio());
        }
        for (Arbusto arbusto : arbustos) {
            System.out.println("Arbusto: Frutinhas disponíveis = " + arbusto.getFrutinhasDisponiveis());
        }
        for(Coelho coelho : coelhos){
            coelho.viver();
            System.out.println("Coelho: "+coelho.getStatus());
        }
        for(Lobo lobo : lobos){
            lobo.viver();
            System.out.println("Lobo: "+lobo.getStatus());
        }
        
        System.out.println("|-------------------------------------------------------|");
        
    }
    public static void main(String[] args) {
        new Floresta();
    }
}
