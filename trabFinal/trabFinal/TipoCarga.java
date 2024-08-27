package trabFinal;
public class TipoCarga {
    private int numero;
    private String descricao;

    public TipoCarga(int numero, String descricao) {
        this.numero = numero;
        this.descricao = descricao;

    }

    //início getters e setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    //fim getters e setters

    @Override
    public String toString() {
        return "TipoCarga [numero=" + numero + ", descricao=" + descricao + "]";
    }
}