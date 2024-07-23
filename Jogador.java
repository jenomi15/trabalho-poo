public class Jogador {
    private String nome;
    private String cor;
    private String tipo;
    private int numeroCasa;
    private boolean pulaProximaRodada;
    private int soma;
    private boolean iguais;

    // Construtor principal
    public Jogador(String cor, int numeroCasa) {
        this.pulaProximaRodada = false;
        this.cor = cor;
        this.numeroCasa = numeroCasa;
    }

    // Construtor para resultado dos dados
    public Jogador(int soma, boolean iguais) {
        this.soma = soma;
        this.iguais = iguais;
    }

    // Getters e setters
    public int getSoma() {
        return soma;
    }

    public boolean isIguais() {
        return iguais;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public String getTipo() {
        return tipo;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean pulaRodada() {
        return this.pulaProximaRodada;
    }

    public void reiniciarPulo() {
        this.pulaProximaRodada = false;
    }

    public ResultadoDados rolarDados() {
        int dado1 = (int) (Math.random() * 6) + 1;
        int dado2 = (int) (Math.random() * 6) + 1;
        int soma = dado1 + dado2;
        boolean iguais = (dado1 == dado2);
        return new ResultadoDados(soma, iguais);
    }

    @Override
    public String toString() {
        return "Jogador: cor=" + cor + ", numeroCasa=" + numeroCasa;
    }
}

