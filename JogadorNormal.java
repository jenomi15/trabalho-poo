
import java.util.Random;

public class JogadorNormal extends Jogador {
    
    private String Normal; 
    public JogadorNormal(String cor, int numeroCasa ,boolean pulaProximaRodada , int numeroDeJogadas) {
        super(cor, numeroCasa,pulaProximaRodada);
        this.pulaProximaRodada = pulaProximaRodada;
        this.numeroDeJogadas = numeroDeJogadas;
        setTipo(2);
    }

    @Override
    public ResultadoDados rolarDados() {
        // Rolando dois dados
        int dado1 = (int) (Math.random() * 6) + 1; 
        int dado2 = (int) (Math.random() * 6) + 1; 
        int soma = dado1 + dado2;
        
        // Exibindo o resultado dos dados
        System.out.println("O dado 1 rolou: " + dado1);
        System.out.println("O dado 2 rolou: " + dado2);
        System.out.println("a soma dos dados : "+ soma );
        // Verificando se os dados são iguais
        boolean iguais = (dado1 == dado2);
        
        // Retornando o resultado
        return new ResultadoDados(soma, iguais);
    }

    @Override
    public String toString() {
        // Fornecendo uma representação textual clara
        return "Jogador Normal: cor=" + getCor() + ", numeroCasa=" + getNumeroCasa();
    }
}

