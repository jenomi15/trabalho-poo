
import java.util.Random;
public  class JogadorNormal extends Jogador {

    public JogadorNormal(String cor, int numeroCasa) {
        super(cor , numeroCasa);
        
    }

   

    @Override
    public int rolarDados() {
        
        int dado1 = (int) (Math.random() * 6) + 1; 
        int dado2 = (int) (Math.random() * 6) + 1; 
        int soma = dado1 + dado2;
        System.out.println("o dado 1 rolou : " + dado1);
        System.out.println("o dado 2 rolou : " +dado2);
        return soma;
        
        

    }
    
   
}
