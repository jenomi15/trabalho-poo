import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tabuleiro {
    private ArrayList<Casa> tabuleiroJogado;
    private ArrayList<Jogador> jogadores; 
    private String[] tiposDeJogador = {"Azarado", "Normal", "Sortudo"};

    public Tabuleiro(ArrayList<Jogador> jogadores) { 
        this.tabuleiroJogado = new ArrayList<>();
        this.jogadores = jogadores; 
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
    public void removerCorCasa( int posicao , String cor){
        if (posicao>= 0 && posicao < tabuleiroJogado.size()){
            tabuleiroJogado.get(posicao).removerCor(cor);
        }else{
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
        
        do {
            System.out.println("Selecione os tipos de jogadores da rodada");
            for (int o = 0; o < quantidadeDeJogadores; o++) {
               System.out.println("Você está selecionando o jogador " + jogadores.get(o).getCor() + "\nDigite 1 para ele ser Azarado\n2 para ele ser Normal\n3 para ele ser Sortudo");
                int tipoJogador = teclado1.nextInt();
               // TrocaJogador(jogadores.get(o), tipoJogador , o  );  O Problema do codigo esta aqui , na troca de jogador 
            }
            
            for (int a = 0; a < quantidadeDeJogadores; a++) {
                if (jogadores.get(a).pulaRodada()) {
                    System.out.println("O jogador " + jogadores.get(a).getCor() + " está pulando esta rodada.");
                    jogadores.get(a).reiniciarPulo();
                    continue;
                }
                
                System.out.println("Turno do jogador N* " + (a + 1) + " (" + jogadores.get(a).getCor() + "), role os dados.");
                int resultado = jogadores.get(a).rolarDados();
                System.out.println("O jogador " + jogadores.get(a).getCor() + " rolou " + resultado);
    
                int posicaoAntiga = jogadores.get(a).getNumeroCasa();
                System.out.println("Posição antiga do jogador " + jogadores.get(a).getCor() + ": " + posicaoAntiga);
                
                int novaPosicao = posicaoAntiga + resultado;
                System.out.println("Nova posição calculada para o jogador " + jogadores.get(a).getCor() + ": " + novaPosicao);
                
                jogadores.get(a).setNumeroCasa(novaPosicao);
    
                // Verificar se a posição foi atualizada corretamente nessa merda
                System.out.println("Nova posição após setNumeroCasa para o jogador " + jogadores.get(a).getCor() + ": " + jogadores.get(a).getNumeroCasa());
                 
                removerCorCasa(posicaoAntiga, jogadores.get(a).getCor());
                adicionarCorCasa(novaPosicao, jogadores.get(a).getCor());
    
                System.out.println("O jogador " + jogadores.get(a).getCor() + " está agora na casa " + novaPosicao);
    
                verificacaoCasa(novaPosicao, jogadores.get(a));
                imprimirTabuleiro();
    
                // Verificar se o jogador rolou dois dados iguais (não implementado diretamente)
                // if (resultadoDosDadosSaoIguais) {
                //     System.out.println("O jogador " + jogadores.get(a).getCor() + " rolou dois dados iguais e joga novamente.");
                //     a--;  // Permite o jogador a jogar novamente
                // }
            }
        } while (!jogoTerminou());
    }
    


 
    

    public void TrocaJogador(Jogador jogador, int tipoJogador, int a) {
        int numeroCasaAtual = jogador.getNumeroCasa();
        Jogador novoJogador  = new Jogador(jogador.getCor() , jogador.getNumeroCasa() );
    
        if (tipoJogador == 1) {
            novoJogador = new JogadorAzarado(jogador.getCor(), numeroCasaAtual);
        } else if (tipoJogador == 2) {
            novoJogador = new JogadorNormal(jogador.getCor(), numeroCasaAtual);
        } else if (tipoJogador == 3) {
            novoJogador = new JogadorSortudo(jogador.getCor(), numeroCasaAtual);
        } else {
            System.out.println("Tipo de jogador inválido");
            return;
        }
    
        int index = jogadores.indexOf(jogador);
        if (index != -1) {
            jogadores.set(index, novoJogador);
        }
    }
    
    



    private void trocarDeLugarComUltimo(Jogador jogador) {
        Jogador ultimoJogador = jogadores.get(0);
        for (Jogador j : jogadores) {
            if (j.getNumeroCasa() < ultimoJogador.getNumeroCasa()) {
                ultimoJogador = j;
            }
        }

        if (jogador != ultimoJogador) {
            int posicaoAtual = jogador.getNumeroCasa();
            jogador.setNumeroCasa(ultimoJogador.getNumeroCasa());
            ultimoJogador.setNumeroCasa(posicaoAtual);
            System.out.println("O jogador " + jogador.getCor() + " trocou de posição com o jogador " + ultimoJogador.getCor());
        } else {
            System.out.println("O jogador já está na última posição e não pode trocar!");
        }
    }




    private void escolherCompetidorVoltarInicio(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha um competidor para voltar ao início:");
        for (int i = 0; i < jogadores.size(); i++) {
            System.out.println((i + 1) + " - " + jogadores.get(i).getCor());
        }
        int escolha = scanner.nextInt();
        jogadores.get(escolha - 1).setNumeroCasa(0);
        System.out.println("O jogador " + jogadores.get(escolha - 1).getCor() + " voltou para o início!");
    }



    public void verificacaoCasa(int numeroDaCasa, Jogador jogador) {
        if (numeroDaCasa == 10 || numeroDaCasa == 25 || numeroDaCasa == 38) {
            System.out.println("O jogador não joga na próxima rodada.");
            jogador.pulaRodada();
        } else if (numeroDaCasa == 13) {
            System.out.println("Casa surpresa! O jogador deve tirar uma carta.");
            tirarCartaAleatoria(jogador);
        } else if (numeroDaCasa == 5 || numeroDaCasa == 15 || numeroDaCasa == 30) {
            if (!(jogador instanceof JogadorAzarado)) {
                System.out.println("Casa da sorte! O jogador anda 3 casas para frente.");
                jogador.setNumeroCasa(jogador.getNumeroCasa() + 3);
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
        TrocaJogador(jogador, tipoAleatorio);
        System.out.println("O jogador " + jogador.getCor() + " agora é do tipo " + tiposDeJogador[tipoAleatorio - 1]);
    }

    private boolean jogoTerminou() {
        // Verifica se algum jogador atingiu o final do tabuleiro para determinar o término do jogo.
        for (Jogador jogador : jogadores) {
            if (jogador.getNumeroCasa() >= tabuleiroJogado.size()) {
                System.out.println("O jogador " + jogador.getCor() + " venceu!");
                return true;
            }
        }
        return false;
    }
}