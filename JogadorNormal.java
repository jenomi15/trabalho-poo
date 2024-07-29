
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
        
        int dado1 = (int) (Math.random() * 6) + 1; 
        int dado2 = (int) (Math.random() * 6) + 1; 
        int soma = dado1 + dado2;
        
        
        System.out.println("O dado 1 rolou: " + dado1);
        System.out.println("O dado 2 rolou: " + dado2);
        System.out.println("a soma dos dados : "+ soma );
        
        boolean iguais = (dado1 == dado2);
        
        
        return new ResultadoDados(soma, iguais);
    }

    @Override
    public String toString() {
       
        return "Jogador Normal: cor=" + getCor() + ", numeroCasa=" + getNumeroCasa();
    }
}

