package Floresta;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Floresta {
    private HashSet<Arvore> arvores;
    private HashSet<Coelho> coelhos;
    private HashSet<Lobo> lobos;

    public Floresta() {
        System.out.println("| ---------------- Bem-vindo ao Simulador da Floresta!---------------- |");
        System.out.println("| Neste simulador, você poderá acompanhar a vida na floresta           |");
        System.out.println("| e ver como os animais lobos e coelhos interagem.                     |");
        System.out.println("| Vida na floresta muda a cada 3 segundos.                             |");
        System.out.println("| Você poderá escolher quais animais deseja incluir na floresta.       |");
        System.out.println("|----------------------------------------------------------------------|");

        arvores = new HashSet<>();
        coelhos = new HashSet<>();
        lobos = new HashSet<>();

        Scanner scanner = new Scanner(System.in);

        if (obterResposta(scanner, "| Deseja incluir Arvore (Y/N)?                                         |")) {
            Arvore arvore = new Arvore();
            arvores.add(arvore);
        }
        if (obterResposta(scanner, "| Deseja incluir Coelho (Y/N)?                                         |")) {
            Coelho coelho = new Coelho();
            coelhos.add(coelho);
        }
        if (obterResposta(scanner, "| Deseja incluir Lobo (Y/N)?                                           |")) {
            Lobo lobo = new Lobo();
            lobos.add(lobo);
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                atualizarFloresta();
            }
        }, 0, 3 * 1000);
    }

    public void atualizarFloresta() {
        System.out.println("|--------------------- Floresta ------------------------|");
        boolean coelhosVivos = false;
        boolean lobosVivos = false;
        boolean arvoresVivas = false;

        for (Arvore arvore : arvores) {
            if (arvore.getEstagio().equals("morta")) {
                continue;
            }
            arvore.envelhecer(); 
            if (!arvore.getEstagio().equals("morta")) {
                arvoresVivas = true; 
                System.out.println("Arvore: " + arvore.getEstagio());
            }
        }

        for (Coelho coelho : coelhos) {
            if (coelho.estaVivo()) {
                System.out.println("Coelho: " + coelho.getStatus());
                coelhosVivos = true;
                for (Lobo lobo : lobos) {
                    if (lobo.estaVivo()) {
                        coelho.fugir(lobo);
                        if (!coelho.estaVivo()) {
                            System.out.println("Coelho fugiu do Lobo, mas não conseguiu escapar e morreu.");
                        } else {
                            System.out.println("Coelho conseguiu fugir do Lobo.");
                        }
                    }
                }
            } else {
                System.out.println("Coelho: Morto");
            }
        }

        for (Lobo lobo : lobos) {
            if (lobo.estaVivo()) {
                lobo.getVida();
                System.out.println("Lobo: " + lobo.getStatus());
                lobosVivos = true;

                for (Coelho coelho : coelhos) {
                    if (coelho.estaVivo()) {
                        lobo.cacarCoelho(coelho);
                        if (!coelho.estaVivo()) {
                            System.out.println("Lobo conseguiu caçar o Coelho e se alimentou.");
                        } else {
                            System.out.println("Lobo não conseguiu comer o Coelho.");
                        }
                    }
                }
            } else {
                System.out.println("Lobo: Morto");
            }
        }

        if (!coelhosVivos && !lobosVivos) {
            System.out.println("| Todos os coelhos e lobos estão mortos. A simulação será encerrada. |");
            System.exit(0);
        }
        if (!arvoresVivas) {
            System.out.println("| Todas as árvores estão mortas. A simulação será encerrada.          |");
            System.exit(0);
        }
        System.out.println("|-------------------------------------------------------|");
    }

    private boolean obterResposta(Scanner scanner, String pergunta) {
        System.out.println(pergunta);
        String resposta = scanner.nextLine().trim().toLowerCase();
        return resposta.equals("y");
    }

    public static void main(String[] args) {
        new Floresta();
    }
}
