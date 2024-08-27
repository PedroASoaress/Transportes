package trabFinal;
public class Perecivel extends TipoCarga {
    private String origem;
    private int tempoMaxValidade;

    public Perecivel(int numero, String descricao, String origem, int tempoMaxValidade) {
        super(numero, descricao);
        this.origem = origem;
        this.tempoMaxValidade = tempoMaxValidade;

    }

    //início getters e setters
    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public int gettempoMaxValidade() {
        return tempoMaxValidade;
    }

    public void settempoMaxValidade(int tempoMaxValidade) {
        this.tempoMaxValidade = tempoMaxValidade;
    }
    //fim getters e setters

    @Override
    public String toString() {
        return "Perecivel [origem=" + origem + ", tempoMaxValidade=" + tempoMaxValidade + ", " + super.toString() + "]";
    }
    
}