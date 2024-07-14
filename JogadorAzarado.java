
import java.util.Random;
public class JogadorAzarado extends Jogador {

    public JogadorAzarado(String cor ,int numeroCasa) {
        super(cor , numeroCasa);
        
    }

   

    @Override
    public int rolarDados() {
        int soma ;
        do{
        int dado1 = (int) (Math.random() * 6) + 1; 
        int dado2 = (int) (Math.random() * 6) + 1; 
        soma = dado1 + dado2;
        
    }while (soma > 6);
    return soma ;
}
}
