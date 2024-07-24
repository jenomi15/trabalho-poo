
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;


public class Tabuleiro {
    private ArrayList<Casa> tabuleiroJogado;
    private ArrayList<Jogador> jogadores; 
    private List<Integer> casasPularRodada;
    private String[] tiposDeJogador = {"Azarado", "Normal", "Sortudo"};

    public Tabuleiro(ArrayList<Jogador> jogadores) { 
        this.tabuleiroJogado = new ArrayList<>();
        this.jogadores = jogadores; 
        this.casasPularRodada = Arrays.asList(10, 25, 38);
    }

    public void criacaoDoTabuleiro(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            tabuleiroJogado.add(new Casa(i)); 
        }
    }
    
    public void adicionarCorCasa(int posicao, String cor) {
        if (posicao >= 0 && posicao < tabuleiroJogado.size()) {
            tabuleiroJogado.get(posicao).adicionarCor(cor);
        } else {
            System.out.println("Posição inválida");
        }
    }
    
    public void removerCorCasa(int posicao, String cor) {
        if (posicao >= 0 && posicao < tabuleiroJogado.size()) {
            tabuleiroJogado.get(posicao).removerCor(cor);
        } else {
            System.out.println("Posição inválida");
        }
    }

    public void imprimirTabuleiro() {
        for (Casa casa : tabuleiroJogado) {
            System.out.println(casa);
        }
    }

    public void TurnoDoJogo() {
        Scanner teclado1 = new Scanner(System.in);
        int quantidadeDeJogadores = jogadores.size();
        int y = 1; // Inicializa y com um valor apropriado
        
        do {
            System.out.println("Selecione os tipos de jogadores da rodada " + y);
            for (int o = 0; o < quantidadeDeJogadores; o++) {
                System.out.println("Você está selecionando o jogador " + jogadores.get(o).getCor() +
                        "\nDigite 1 para ele ser Azarado\n2 para ele ser Normal\n3 para ele ser Sortudo");
                int tipoJogador = teclado1.nextInt();
                
                // Troca o tipo do jogador usando o método TrocaJogador
                TrocaJogador(jogadores.get(o), tipoJogador, o);
                
                for (Jogador jogador : jogadores) {
                    System.out.println(jogador);
                }
            }
    
            for (int a = 0; a < quantidadeDeJogadores; a++) {
                if (jogadores.get(a).pulaRodada()) {
                    System.out.println("O jogador " + jogadores.get(a).getCor() + " está pulando esta rodada.");
                    jogadores.get(a).reiniciarPulo();
                    continue; // Passa para o próximo jogador
                }
            
                System.out.println("Turno do jogador N* " + (a + 1) + " (" + jogadores.get(a).getCor() + "), role os dados.");
                ResultadoDados resultado = jogadores.get(a).rolarDados();
                int posicaoAntiga = jogadores.get(a).getNumeroCasa();
                System.out.println(" \n Posição antiga do jogador " + jogadores.get(a).getCor() + ": " + posicaoAntiga);
    
                int novaPosicao = posicaoAntiga + resultado.getSoma();
                if (novaPosicao > 39) {
                    novaPosicao = 39;  // Ajusta para a última casa válida
                }
                System.out.println("\n Nova posição calculada para o jogador " + jogadores.get(a).getCor() + ": " + novaPosicao);
    
                jogadores.get(a).setNumeroCasa(novaPosicao);
    
                removerCorCasa(posicaoAntiga, jogadores.get(a).getCor());
                adicionarCorCasa(novaPosicao, jogadores.get(a).getCor());
    
                System.out.println("O jogador " + jogadores.get(a).getCor() + " está agora na casa " + jogadores.get(a).getNumeroCasa());
    
                verificacaoCasa(jogadores.get(a).getNumeroCasa(), jogadores.get(a));
                imprimirTabuleiro();
                System.out.println(" \n Deseja continuar ? \n 1 - sim \n 2 - não ");
                Scanner teclado3 = new Scanner(System.in);
                int p = teclado3.nextInt();
                
    
                if (resultado.isIguais()) {
                    System.out.println("O jogador " + jogadores.get(a).getCor() + " rolou dois dados iguais e joga novamente.");
                    a--; // o corno joga de novo
                }
            }
    
            y++; // Incrementa o número da rodada
        } while (!jogoTerminou());
        // so ordena msm
           Collections.sort(jogadores, new Comparator<Jogador>() {
           @Override
             public int compare(Jogador j1, Jogador j2) {
               return Integer.compare(j2.getNumeroCasa(), j1.getNumeroCasa());
            }
        });

   
    System.out.println("Posições dos jogadores:");
    for (int i = 0; i < jogadores.size(); i++) {
        Jogador jogador = jogadores.get(i);
        System.out.println((i + 1) + "º lugar: Jogador " + jogador.getCor() + " - Casa " + jogador.getNumeroCasa());
    }
}
        
    
    

 
    
    public void TrocaJogador(Jogador jogador, int tipoJogador, int a) {
        int numeroCasaAtual = jogador.getNumeroCasa();
        Jogador novoJogador;
    
        // Cria o novo jogador com base no tipo selecionado
        switch (tipoJogador) {
            case 1:
                novoJogador = new JogadorAzarado(jogador.getCor(), numeroCasaAtual, jogador.pulaRodada());
                break;
            case 2:
                novoJogador = new JogadorNormal(jogador.getCor(), numeroCasaAtual, jogador.pulaRodada());
                break;
            case 3:
                novoJogador = new JogadorSortudo(jogador.getCor(), numeroCasaAtual, jogador.pulaRodada());
                break;
            default:
                System.out.println("Tipo de jogador inválido");
                return;
        }
    
        // Substitui o jogador atual pelo novo jogador na lista
        int index = jogadores.indexOf(jogador);
        if (index != -1) {
            jogadores.set(index, novoJogador);
        }
    }
    
    
    



    
    
   private void trocarDeLugarComUltimo(Jogador jogador) {
    List<Jogador> ultimosJogadores = jogadores.stream()
        .filter(j -> j.getNumeroCasa() == Collections.min(jogadores, Comparator.comparingInt(Jogador::getNumeroCasa)).getNumeroCasa())
        .collect(Collectors.toList());

    Jogador ultimoJogador;

    if (ultimosJogadores.size() > 1) {
        ultimoJogador = escolherAleatoriamente(ultimosJogadores);
    } else {
        ultimoJogador = ultimosJogadores.get(0);
    }

    if (jogador != ultimoJogador) {
        int posicaoAtual = jogador.getNumeroCasa();
        jogador.setNumeroCasa(ultimoJogador.getNumeroCasa());
        ultimoJogador.setNumeroCasa(posicaoAtual);

        tabuleiroJogado.get(jogador.getNumeroCasa()).removerCor(ultimoJogador.getCor());
        tabuleiroJogado.get(posicaoAtual).removerCor(jogador.getCor());
        tabuleiroJogado.get(jogador.getNumeroCasa()).adicionarCor(jogador.getCor());
        tabuleiroJogado.get(ultimoJogador.getNumeroCasa()).adicionarCor(ultimoJogador.getCor());

        System.out.println("O jogador " + jogador.getCor() + " trocou de posição com o jogador " + ultimoJogador.getCor());
    } else {
        System.out.println("O jogador já está na última posição e não pode trocar!");
    }
}

private Jogador escolherAleatoriamente(List<Jogador> jogadores) {
    Random random = new Random();
    return jogadores.get(random.nextInt(jogadores.size()));
}



    // errinho aqui na questao de quando as vezes voltamos um jogador , outro vai para o inicio , mas apenas na impressao , provavelmente na ultima linha 
    private void escolherCompetidorVoltarInicio(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha um competidor para voltar ao início:");
        for (int i = 0; i < jogadores.size(); i++) {
            System.out.println((i + 1) + " - " + jogadores.get(i).getCor());
        }
        int escolha = scanner.nextInt();
        tabuleiroJogado.get(jogadores.get(escolha - 1 ).getNumeroCasa()).removerCor(jogadores.get(escolha-1).getCor());
        jogadores.get(escolha - 1).setNumeroCasa(0);
        System.out.println("O jogador " + jogadores.get(escolha - 1).getCor() + " voltou para o início!");
        tabuleiroJogado.get(0).adicionarCor(jogadores.get(escolha-1).getCor()); // aqui oh 
                                                        // antes tava jogador.getcor() KKKK
    }


    public void verificacaoCasa(int numeroDaCasa, Jogador jogador) {
        if (numeroDaCasa == 10 || numeroDaCasa == 25 || numeroDaCasa == 38) {
            System.out.println("O jogador não joga na próxima rodada.");
            jogador.pularProximaRodada();
        } else if (numeroDaCasa == 13) {
            System.out.println("Casa surpresa! O jogador deve tirar uma carta.");
            tirarCartaAleatoria(jogador);
        } else if (numeroDaCasa == 5 || numeroDaCasa == 15 || numeroDaCasa == 30) {
            if (!(jogador instanceof JogadorAzarado)) {
                System.out.println("Casa da sorte! O jogador anda 3 casas para frente.");
                tabuleiroJogado.get(numeroDaCasa).removerCor(jogador.getCor());
                jogador.setNumeroCasa(jogador.getNumeroCasa() + 3);
                tabuleiroJogado.get(jogador.getNumeroCasa()).adicionarCor(jogador.getCor());
            }
        } else if (numeroDaCasa == 17 || numeroDaCasa == 27) {
            System.out.println("O jogador escolhe um competidor para voltar ao início.");
            escolherCompetidorVoltarInicio(jogador);
        } else if (numeroDaCasa == 20 || numeroDaCasa == 35) {
            System.out.println("Casa mágica! O jogador troca de lugar com o último jogador.");
            trocarDeLugarComUltimo(jogador);
        }
    }
    






    private void tirarCartaAleatoria(Jogador jogador) {
        Random rand = new Random();
        int tipoAleatorio = rand.nextInt(3) + 1;
        TrocaJogador(jogador, tipoAleatorio, 1);
        System.out.println("O jogador " + jogador.getCor() + " agora é do tipo " + tiposDeJogador[tipoAleatorio - 1]);
    }

    private boolean jogoTerminou() {
        // Verifica se algum jogador atingiu o final do tabuleiro para determinar o término do jogo , E POR ALGUM MOTIVO NA ULTIMA VEZ QUE EU RODEI NAO GANHOU QND PASSOU DO LIMITE
        for (Jogador jogador : jogadores) {
            if (jogador.getNumeroCasa() >= tabuleiroJogado.size() - 1 ) {
                System.out.println("O jogador " + jogador.getCor() + " venceu!");
                return true;
            }
        }
        return false;
    }
}