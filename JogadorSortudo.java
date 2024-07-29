import java.util.Random;

public class JogadorSortudo extends Jogador {
   
    private String Sortudo; 
    public JogadorSortudo(String cor, int numeroCasa , boolean pulaProximaRodada , int numeroDeJogadas) {
        super(cor, numeroCasa,pulaProximaRodada);
        this.pulaProximaRodada = pulaProximaRodada;
        this.numeroDeJogadas = numeroDeJogadas;
        setTipo(3);
    }

    @Override
    public ResultadoDados rolarDados() {
        int soma, dado1, dado2;

        do {
            dado1 = (int) (Math.random() * 6) + 1;
            dado2 = (int) (Math.random() * 6) + 1;
            soma = dado1 + dado2;
            
            // Exibindo o resultado dos dados
            System.out.println("O dado 1 rolou: " + dado1);
            System.out.println("O dado 2 rolou: " + dado2);
            System.out.println("a soma dos dados : "+ soma );
        } while (soma < 6); // Continua rolando até a soma ser maior ou igual a 6
        
        
        boolean iguais = (dado1 == dado2);
        
        // Retornando o resultado
        return new ResultadoDados(soma, iguais);
    }

    @Override
    public String toString() {
        // Fornecendo uma representação textual clara
        return "Jogador Sortudo: cor=" + getCor() + ", numeroCasa=" + getNumeroCasa();
    }
}
