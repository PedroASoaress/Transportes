package trabFinal;
public class Duravel extends TipoCarga {
    private String setor;
    private String materialPrincipal;

    public Duravel(int numero, String descricao, String setor, String materialPrincipal) {
        super(numero, descricao);
        this.setor = setor;
        this.materialPrincipal = materialPrincipal;

    }

    //início getter e setters
    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getMaterialPrincipal() {
        return materialPrincipal;
    }

    public void setMaterialPrincipal(String materialPrincipal) {
        this.materialPrincipal = materialPrincipal;
    }
    //fim getters e setters

    @Override
    public String toString() {
        return "Duravel [setor=" + setor + ", materialPrincipal=" + materialPrincipal + ", " + super.toString() + "]";
    }
    
    
}