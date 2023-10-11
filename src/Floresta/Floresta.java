    package Floresta;

    import java.util.HashSet;
    import java.util.Scanner;
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
            System.out.println("Os coelhos buscam comida nos arbustos e tentam fugir dos lobos se não morrem.");
            System.out.println("Os lobos tentam caçar os coelhos, mas também precisam se alimentar para sobreviver.");

            arvores = new HashSet<>();
            arbustos = new HashSet<>();
            coelhos = new HashSet<>();
            lobos = new HashSet<>();

            Scanner scanner = new Scanner(System.in);
            
            if (obterResposta(scanner, "Deseja incluir árvores (Y/N)?")) {
                Arvore arvore = new Arvore();
                arvores.add(arvore);
            }

            if (obterResposta(scanner, "Deseja incluir arbustos (Y/N)?")) {
                Arbusto arbusto = new Arbusto();
                arbustos.add(arbusto);
            }

            if (obterResposta(scanner, "Deseja incluir coelhos (Y/N)?")) {
                Coelho coelho = new Coelho();
                coelhos.add(coelho);
            }

            if (obterResposta(scanner, "Deseja incluir lobos (Y/N)?")) {
                Lobo lobo = new Lobo();
                lobos.add(lobo);
            }

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    atualizarFloresta();
                }
            }, 0, 3 * 1000); // Atualize a cada 3 segundos (tempo em milissegundos)
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
            for (Coelho coelho : coelhos) {
                coelho.viver();
                if (coelho.estaVivo()) {
                    System.out.println("Coelho: " + coelho.getStatus());
                    
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
                    lobo.viver();
                    System.out.println("Lobo: " + lobo.getStatus());
                    
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
