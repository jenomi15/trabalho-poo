import java.util.ArrayList;
import java.util.List;

public class Casa {
    private int numero;
    private List<String> cores;

    public Casa(int numero) {
        this.numero = numero;
        this.cores = new ArrayList<>();
    }

    public void adicionarCor(String cor) {
        cores.add(cor);
    }

    public void removerCor(String cor) {
        cores.remove(cor);
    }

    @Override
    public String toString() {
        return "Casa " + numero + " -> Cores: " + cores;
    }
}

