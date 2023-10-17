    package Floresta;

    import java.util.HashSet;
    import java.util.Scanner;
    import java.util.Timer;
    import java.util.TimerTask;

    public class Floresta {
        private HashSet<Arvore> arvores;
        private HashSet<Coelho> coelhos;
        private HashSet<Lobo> lobos;
        private HashSet<Pantera> panteras;
        private HashSet<Cervo> cervos;
        private int Tempo;

        public Floresta() {
            System.out.println("| ---------------- Bem-vindo ao Simulador da Floresta!---------------- |");
            System.out.println("| Neste simulador, você poderá acompanhar a vida na floresta           |");
            System.out.println("| e ver como os animais lobos,coelhos,cervos e panteras interagem.     |");
            System.out.println("| Vida na floresta muda a cada 3 segundos.                             |"); 
            System.out.println("| Você poderá escolher quais animais deseja incluir na floresta.       |");           
            System.out.println("|----------------------------------------------------------------------|");


            arvores = new HashSet<>();
            coelhos = new HashSet<>();
            lobos = new HashSet<>();
            panteras = new HashSet<>();
            cervos = new HashSet<>();
            Tempo = 0;

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
            if(obterResposta(scanner, "| Deseja incluir Pantera (Y/N)?                                        |")){
                Pantera pantera = new Pantera();
                panteras.add(pantera);
            }
            if(obterResposta(scanner, "| Deseja incluir Cervo (Y/N)?                                          |")){
                Cervo cervo = new Cervo();
                cervos.add(cervo);
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
            Tempo += 3;
            System.out.println("|--------------------- Floresta ------------------------|");
            System.out.println("| Tempo: " + Tempo + " segundos                         |");
        
            for (Arvore arvore : arvores) {
                arvore.envelhecer();
                System.out.println("Árvores: Estágio = " + arvore.getEstagio());
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
        
            for (Pantera pantera : panteras) {
                if (pantera.estaVivo()) {
                    pantera.viver();
                    System.out.println("Pantera: " + pantera.getStatus());
                    for (Cervo cervo : cervos) {
                        if (cervo.estaVivo()) {
                            pantera.comerCervo(cervo);
                            if (!cervo.estaVivo()) {
                                System.out.println("Pantera conseguiu caçar o Cervo e se alimentou.");
                            } else {
                                System.out.println("Pantera não conseguiu comer o Cervo.");
                            }
                        }
                    }
                } else {
                    System.out.println("Pantera: Morto");
                }
            }
        
            for (Cervo cervo : cervos) {
                cervo.viver();
                if (cervo.estaVivo()) {
                    System.out.println("Cervo: " + cervo.getStatus());
                    for (Pantera pantera : panteras) {
                        if (pantera.estaVivo()) {
                            cervo.fugir(pantera);
                            if (!cervo.estaVivo()) {
                                System.out.println("Cervo fugiu da Pantera, mas não conseguiu escapar e morreu.");
                            } else {
                                System.out.println("Cervo conseguiu fugir da Pantera.");
                            }
                        }
                    }
                } else {
                    System.out.println("Cervo: Morto");
                }
            } 
        
            if (Animal.getAnimaisVivos() == 0) {
                System.out.println("Todos os animais morreram. A simulação terminou.");
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
