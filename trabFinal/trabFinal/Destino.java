package trabFinal;
public class Destino {
    private int codDestino;
    private String nome;
    private String cidade;

    public Destino(int codDestino, String nome, String cidade){
        this.codDestino = codDestino;
        this.nome = nome;
        this.cidade = cidade;
        
    }

    //inicio getters e setters
    public int getcodDestino() {
        return codDestino;
    }

    public void setCodigo(int codDestino) {
        this.codDestino = codDestino;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    //fim getters e setters

    @Override
    public String toString() {
        return "Destino [código do destino=" + codDestino + ", nome=" + nome + ", cidade=" + cidade + "]";
    }
    
}
