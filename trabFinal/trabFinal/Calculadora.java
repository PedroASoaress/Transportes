package trabFinal;

public class Calculadora extends DefineSitCC{

    public double frete;
    public double precoDistancia;
    public double precoPeso;
    public Carga carga;
    public Caminhao caminhao;

    public Calculadora() {
        this.carga = null;
        this.caminhao = null;

    }

    //início getters e setters
    public double getFrete() {
        return frete;
    }

    public double getPrecoDistancia() {
        return precoDistancia;
    }

    public double getPrecoPeso() {
        return precoPeso;
    }

    public Carga getCarga() {
        return carga;
    }

    public void setCarga(Carga carga) {
        this.carga = carga;
    }

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }
    //fim getters e setters

    public double calculaFrete(){
        double frete = (getPrecoDistancia()*getPrecoPeso());
        this.frete = frete;
        return frete;
    }

    public double precoDistancia(){
            double dist = carga.getDistancia();
            double km = caminhao.getCustoporKm();
            double precoDistancia = (dist * km);
            this.precoDistancia = precoDistancia;
            return precoDistancia;
    }

    public double precoPeso() {
        TipoCarga tipoCarga = carga.getTipoCarga();
    
        if (tipoCarga instanceof Perecivel) {
            double precoPeso = carga.getPeso() * 2;
            this.precoPeso = precoPeso;
            return precoPeso;

        } else if (tipoCarga instanceof Duravel) {
            double precoPeso = (carga.getPeso() * 1.5);
            this.precoPeso = precoPeso;
            return precoPeso;

        } else {
            throw new IllegalArgumentException("Erro: Tipo de carga não reconhecido para o cálculo de preço por peso.");
        }
    }

    public String getCaminhaoCarga(){
        return "Carga e caminhão a serem calculados: \n" + carga.toString() + "\n" + caminhao.toString();
    }

    @Override
    public String toString() {
        return "\nCalculadora [frete=" + frete + ", precoDistancia=" + precoDistancia + ", precoPeso=" + precoPeso + "]";
    }

    
}

