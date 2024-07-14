public  class Jogador {
    private String nome;
    private String cor;
    private  String tipo ;
    private int numeroCasa;
   
   
   
   
   
   public Jogador(  String cor , int numeroCasa ){
    
       this.cor = cor ;
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



    public int rolarDados( ) { // se eu deixasse abstrato seria ruim de instanciar os jogadores depois 
        int dado1 = (int) (Math.random() * 6) + 1; 
        int dado2 = (int) (Math.random() * 6) + 1; 
        int soma = dado1 + dado2;
        return soma;
    }
        
    }



