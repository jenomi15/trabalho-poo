
import java.util.Random;

public class JogadorAzarado extends Jogador {

    public JogadorAzarado(String cor, int numeroCasa) {
        super(cor, numeroCasa);
    }

    @Override
    public ResultadoDados rolarDados() {
        int soma, dado1, dado2;
        do {
            dado1 = (int) (Math.random() * 6) + 1;
            dado2 = (int) (Math.random() * 6) + 1;
            soma = dado1 + dado2;
        } while (soma > 6);

        System.out.println("O dado 1 rolou: " + dado1);
        System.out.println("O dado 2 rolou: " + dado2);
        boolean iguais = (dado1 == dado2);
        return new ResultadoDados(soma, iguais);
    }
}

