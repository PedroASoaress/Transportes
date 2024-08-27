package trabFinal;

public class Carga implements SituacaoCarga{
    private int codCarga;
    private double peso;
    private double valorDeclarado;
    private int tempoMaximo;
    private String situacao;
    private TipoCarga tipoCarga;
    private Destino localOrigem;
    private Destino localDestino;
    private double distancia;
    private Cliente cliente;

    public Carga(int codCarga, double peso, double valorDeclarado, int tempoMaximo){
        this.codCarga = codCarga;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
        this.tempoMaximo = tempoMaximo;
        this.situacao = "PENDENTE";
        this.distancia = 50;
        
    }

    //CLIENTE

    //início getters e setters
    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public TipoCarga getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(TipoCarga tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public int getcodCarga(){
        return codCarga;
    }

    public void setcodCarga(int codCarga) {
        this.codCarga = codCarga;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public int getTempoMaximo() {
        return tempoMaximo;
    }

    public void setTempoMaximo(int tempoMaximo) {
        this.tempoMaximo = tempoMaximo;
    }

    public Destino getLocalOrigem() {
        return localOrigem;
    }

    public void setLocalOrigem(Destino localOrigem) {
            this.localOrigem = localOrigem;
    }

    public Destino getDestino() {
        return localDestino;
    }

    public void setDestino(Destino destino) {
            this.localDestino = destino;
    }

    public double getDistancia(){
        return distancia;
    }

    public void setDistancia(double distancia){
        this.distancia = distancia;
    }

    //interface
    @Override
    public String getSituacao() {
        return situacao;
    }

    @Override
    public void setSituacao(String situacao) {
        if (situacao != null) {
            String situacaoAux = situacao.toUpperCase(); // Convertendo para maiúsculas para evitar problemas de maiúsculas/minúsculas
    
            if ("PENDENTE".equals(situacaoAux) || "LOCADA".equals(situacaoAux) ||
                "CANCELADA".equals(situacaoAux) || "FINALIZADA".equals(situacaoAux)) {
                this.situacao = situacaoAux;
            } else {
                throw new IllegalArgumentException("Situação inválida: " + situacao);
            }
        } else {
            throw new IllegalArgumentException("A situação não pode ser nula.");
        }
    }
    //fim getters e setters

    @Override
    public String toString() {
        return "Carga [código da carga=" + codCarga + ", peso=" + peso + ", valor declarado=" + valorDeclarado
                + ", tempo máximo=" + tempoMaximo + ", situacao=" + situacao + ", local de origem=" + localOrigem.getNome()
                + ", local de destino=" + localDestino.getNome() + ", distância=" + distancia +"]";
    }

}
