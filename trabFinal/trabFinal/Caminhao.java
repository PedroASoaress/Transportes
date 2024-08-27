package trabFinal;
public class Caminhao{
    private String nome;
    private double velocidade;
    private double autonomia; //quantos kms pode percorrer com um único tanque de combustível
    private double custoporKm;
    private int codCaminhao;

    public Caminhao(String nome, double velocidade, double autonomia, double custoporKm, int codCaminhao){
        this.nome = nome;
        this.velocidade = velocidade;
        this.autonomia = autonomia;
        this.custoporKm = custoporKm;
        this.codCaminhao = codCaminhao;
        
    }
    
    //início getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public double getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(double autonomia) {
        this.autonomia = autonomia;
    }

    public double getCustoporKm() {
        return custoporKm;
    }

    public void setCustoporKm(double custoporKm) {
        this.custoporKm = custoporKm;
    }

    public int getcodCaminhao() {
        return codCaminhao;
    }

    public void setCodigo(int codCaminhao) {
        this.codCaminhao = codCaminhao;
    }
    //fim getters e setters

    @Override
    public String toString() {
        return "Caminhao [código do caminhão=" + codCaminhao + ", nome=" + nome + ", velocidade=" + velocidade + ", autonomia=" + autonomia + ", custo por Km="
                + custoporKm + "]";
                
    }

    public Object getCustoPorKm() {
        return null;
    }
}