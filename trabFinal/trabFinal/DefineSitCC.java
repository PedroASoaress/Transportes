package trabFinal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DefineSitCC {
    private ArrayList<Carga> listaCargas;
    private ArrayList<Caminhao> listaCaminhao;
    private ArrayList<Calculadora> listaCalculadora;
    private List<String> listaSituacao;
    private Carga cargaRemovida;

    public DefineSitCC(){
        listaCargas = new ArrayList<>();
        listaCaminhao = new ArrayList<>();
        listaCalculadora = new ArrayList<>();
        listaSituacao = new ArrayList<>();
        this.cargaRemovida = cargaRemovida;
                
    }
    
    public Carga getCargaRemovida() {
        return cargaRemovida;
    }

    public void setCargaRemovida(Carga cargaRemovida) {
        this.cargaRemovida = cargaRemovida;
    }

    public boolean caminhaoEstaVazio(){
        if(listaCaminhao.isEmpty() == false){
            return false;
        }
        try{
        throw new IllegalArgumentException(" Erro: A fila de caminhões está vazia.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true; 
        }
    }

    public boolean cargaEstaVazio(){
        if(listaCargas.isEmpty() == false){
            return false;
        }
        try{
        throw new IllegalArgumentException(" Erro: A fila de cargas está vazia.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true; 
        }
    }

    public boolean situacaoEstaVazio(){
                if(listaSituacao.isEmpty() == false){
            return false;
        }
        try{
        throw new IllegalArgumentException(" Erro: A fila de situações está vazia.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    public int existeCarga(int codCarga){
        for (int i = 0; i < listaCargas.size(); i++) {
            if(listaCargas.get(i).getcodCarga() == codCarga){
                //carga está cadastrada
                return i;

            }
        }
        return -1;

    }

    public int existeCaminhao(int codCaminhao){
        for (int i = 0; i < listaCaminhao.size(); i++) {
            if(listaCaminhao.get(i).getcodCaminhao() == codCaminhao){
                //caminhão está cadastrado
                return i;

            }
        }
        return -1;

    }
    
    
    private int encontrarCaminhaoDisponivel() {
        caminhaoEstaVazio();
        List<Integer> indicesCaminhao = new ArrayList<>();
        List<Integer> indicesCarga = new ArrayList<>();
        
    
        //salva os índices dos caminhões
        for (int i = 0; i < listaCaminhao.size(); i++) {
            indicesCaminhao.add(i);
        }
    
        //salva os índices das cargas
        for (int i = 0; i < listaCargas.size(); i++) {
            indicesCarga.add(i);
        }
    
        //encontra o primeiro índice de caminhão não associado a uma carga
        for (int i = 0; i < indicesCaminhao.size(); i++) {
            if (!indicesCarga.contains(indicesCaminhao.get(i))) {
                return indicesCaminhao.get(i);
            }
        }
    
        //se todos os caminhões estão associados a uma carga, retorne o próximo índice
        return -1;
    }

    public boolean addCaminhao(Caminhao caminhao){
        if(existeCaminhao(caminhao.getcodCaminhao()) != -1){
            try{
            throw new IllegalArgumentException ("Erro: Caminhao já está na fila.");
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        listaCaminhao.add(caminhao);
        System.out.println("Caminhao adicionado com sucesso.");
        return true;

    }

    public void addPedido(Carga carga) {
        int index = encontrarCaminhaoDisponivel();
        int indexCarga = carga.getcodCarga();

        if(existeCarga(indexCarga) != -1){
            try{
            throw new IllegalArgumentException(" Erro: Carga já foi adicionada.");
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            
        }else if(index == -1){
            carga.setSituacao("CANCELADA");
            try{
            throw new IllegalArgumentException(" Erro: Não existe caminhões disponíveis, carga foi CANCELADA.");
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }else{
            
        listaCargas.add(index, carga);
        listaSituacao.add(index, "LOCADA");
        carga.setSituacao("LOCADA");
        System.out.println("Pedido adicionado com sucesso.");

    
        Calculadora calc = new Calculadora(); 
        Caminhao caminhao = listaCaminhao.get(index);

        calc.setCaminhao(caminhao);
        calc.setCarga(carga);
        System.out.println(calc.getCaminhaoCarga());
    
        calc.precoDistancia();
        calc.precoPeso();
        calc.calculaFrete();
    
        System.out.println(calc.toString());
    
        listaCalculadora.add(calc);
    
    }
}

    public void alteraSituacaoCarga(Carga carga, String situacao){
        cargaEstaVazio();
        Scanner input = new Scanner(System.in);
        int index = existeCarga(carga.getcodCarga());

        Caminhao caminhao = listaCaminhao.get(index);

        if (carga.getSituacao().equalsIgnoreCase("FINALIZADO")) {
            try{
            throw new IllegalArgumentException(" Erro: A carga está em situação finalizada.");
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

            System.out.println("Dados atuais da carga: " + carga.getSituacao());


            String novaSituacao = situacao; 
            int indexBusca = buscaPedido(carga, caminhao);

            if(novaSituacao.equalsIgnoreCase("CANCELADA")){
                listaSituacao.add(indexBusca, "CANCELADA");
                carga.setSituacao("CANCELADA");
                listaCargas.remove(carga);
                
                System.out.println("Carga cancelada com sucesso.");

            }else if(novaSituacao.equalsIgnoreCase("PENDENTE")){
                listaSituacao.add(indexBusca, "PENDENTE");
                carga.setSituacao("PENDENTE");
                listaCargas.remove(carga);
                
                System.out.println("Carga retornou à pendente com sucesso.");

            }else if(novaSituacao.equalsIgnoreCase("FINALIZADA")){
                listaSituacao.add(indexBusca, "FINALIZADA");
                carga.setSituacao("FINALIZADA");
                listaCargas.remove(carga);
                
                
                System.out.println("Carga foi finalizada com sucesso.");


            }else if(novaSituacao.equalsIgnoreCase("NÃO ATENDIDA")){
                listaSituacao.remove(indexBusca);
                carga.setSituacao("CANCELADA");
                listaCargas.remove(carga);
                
                System.out.println("Carga foi não atendida foi cancelada.");

            }else{
                try{
            throw new IllegalArgumentException(" Erro: Não é possível alterar o entado da carga: " + carga.getSituacao() + " para " + novaSituacao + ".");
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int buscaIndiceCarga(Carga carga) {
        cargaEstaVazio();
        int index = listaCargas.indexOf(carga);
        return index;
    }

    public int buscaIndiceCaminhao(Caminhao caminhao) {
        caminhaoEstaVazio();
        int index = listaCaminhao.indexOf(caminhao);
        return index;
    }

    public int buscaPedido(Carga carga, Caminhao caminhao){
        caminhaoEstaVazio();
        cargaEstaVazio();
        int index;

        if(buscaIndiceCarga(carga) == buscaIndiceCaminhao (caminhao)){
            index = buscaIndiceCarga(carga);
            return index;        
        }else{
        return -1;
        }
    }

    public int buscaPedidoString(Carga carga, Caminhao caminhao){
        caminhaoEstaVazio();
        cargaEstaVazio();
        int index1 = buscaIndiceCarga(carga);
        int index2 = buscaIndiceCaminhao (caminhao);


        if(index1 == index2){
            int index = index2;
            System.out.println("Pedido está cadastrado");
            return index;
        }else{
            return -1;
        
        
        }
    }
    
    
}
