
import java.util.ArrayList;
import java.util.Scanner;

public class Tabuleiro {
    private ArrayList<Casa> tabuleiroJogado;
    private ArrayList<Jogador> jogadores; 

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

    public void imprimirTabuleiro() {
        for (Casa casa : tabuleiroJogado) {
            System.out.println(casa);
        }
    }

    public void TurnoDoJogo(int quantidadeDeJogadores) {
        Scanner teclado1 = new Scanner(System.in);
        do{
            System.out.println("Selecione os tipos de jogadores da rodada");
            for (int o = 0; o < quantidadeDeJogadores; o++) {
                System.out.println("Você está selecionando o jogador " + jogadores.get(o).getCor() + "\nDigite 1 para ele ser Azarado\n2 para ele ser Normal\n3 para ele ser Sortudo");
                int tipoJogador = teclado1.nextInt();
                TrocaJogador(jogadores.get(o), tipoJogador); 
            }
            for (int a = 0 ; a < quantidadeDeJogadores ; a++){
            System.out.println("turno do jogador N"+ a +  "role os dados ");
            int resultado = jogadores.get(a).rolarDados();
            System.out.println("O jogador " + jogadores.get(a).getCor() + " rolou " + resultado);
            int novaPosicao = jogadores.get(a).getNumeroCasa() + resultado;
            jogadores.get(a).setNumeroCasa(novaPosicao);
            verificacaoCasa(novaPosicao);
       

            }



























            
        } while (0 == 40); // casa 40 não possui jogador) esse 0 == 40 não é a condição, está aí apenas para não dar erro, a condição correta vai ser acrescentada depois
    
    }
    public void verificacaoCasa(int numeroDacasa) {
        // Vai verificar se a casa em que o jogador está possui algum efeito diferenciado 


















    }

    public void TrocaJogador(Jogador jogador, int tipoJogador) {
        Jogador novoJogador;
        if (tipoJogador == 1) {
            novoJogador = new JogadorAzarado(jogador.getCor(), jogador.getNumeroCasa());
        } else if (tipoJogador == 2) {
            novoJogador = new JogadorNormal(jogador.getCor(), jogador.getNumeroCasa());
        } else if (tipoJogador == 3) {
            novoJogador = new JogadorSortudo(jogador.getCor() , jogador.getNumeroCasa());
        } else {
            System.out.println("Tipo de jogador inválido");
            return;
        }

        int index = jogadores.indexOf(jogador);
        if (index != -1) {
            jogadores.set(index, novoJogador);
        }
    }
}

