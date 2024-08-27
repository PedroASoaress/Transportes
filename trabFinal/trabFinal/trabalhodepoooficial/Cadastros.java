package trabFinal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Cadastros {
    private static ArrayList<Caminhao> cadastroCaminhoes;
    private static ArrayList<Cliente> cadastroClientes;
    private static ArrayList<Destino> cadastroDestinos;
    private static ArrayList<TipoCarga> cadastroTipoCarga; //polimorfismo, TipoCarga = Perecivel/Duravel
    private static ArrayList<Carga> cadastroCargas;
    private static ArrayList<Carga> cargasPendentes; //todas as cargas de situacao pendente
    private DefineSitCC define;

    public Cadastros(){
        cadastroCaminhoes = new ArrayList<>();
        cadastroClientes = new ArrayList<>();
        cadastroDestinos = new ArrayList<>();
        cadastroTipoCarga = new ArrayList<>();
        cadastroCargas = new ArrayList<>();
        cargasPendentes = new ArrayList<>();
        define = new DefineSitCC();

    }

    //CADASTROS =============================================================================================================================
    public String cadastrarCaminhao(String nome, double velocidade, double autonomia, double custoporKm, int codCaminhao){
            if (buscaCadCaminhao(nome) != -1) {
            try {
                throw new IllegalArgumentException(" Erro: Já existe um caminhão com o nome indicado.");
            }catch (IllegalArgumentException e) {
                return e.getMessage();
            }
            }else{
            //cadastra caminhão
            Caminhao novoCaminhao = new Caminhao(nome, velocidade, autonomia, custoporKm, codCaminhao);
            cadastroCaminhoes.add(novoCaminhao);
            cadastroCaminhoes.sort((c1, c2) -> c1.getNome().compareTo(c2.getNome()));
            
            System.out.println(toStringCaminhao(nome));
            return "Cadastro realizado.";

            }
    }

    public String cadastrarCliente(int codCliente, String nome, int telefone){
            if (buscaCadCliente(codCliente) != -1) {
            try {
                throw new IllegalArgumentException(" Erro: Já existe um cliente com o código indicado.");
            }catch (IllegalArgumentException e) {
                return e.getMessage();
            }
            }else{
                //cadastra novo cliente
                Cliente novoCliente = new Cliente(codCliente, nome, telefone);

                //aqui, não é cadastradas as cargas que se relacionam com cliente pois precisa criar elas antes
                cadastroClientes.add(novoCliente);
                cadastroClientes.sort(Comparator.comparingInt(c -> c.getcodCliente()));

                System.out.println(toStringCliente(codCliente));
                return "Cadastro realizado.";
            }


    }

        public String cadastrarCargaEmCliente(int codCarga, int codCliente){

            Carga carga = buscarCargaPorCod(codCarga);
            Cliente cliente = buscarClientePorCod(codCliente);

            if(carga != null && cliente != null){
                cliente.addCarga(carga);
                return "Carga adicionada com sucesso";

            }else{
            try{
                throw new IllegalArgumentException(" Erro: Não foi possível registrar cliente indicado à carga.");
            }catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        }

    }

    public String cadastrarCarga(int codCarga, double peso, double valorDeclarado, int tempoMaximo, int numeroTipo,
    int codOrigem, int codDestino, double distancia, int codCliente){ 
            Destino localOrigem = buscarDestinoPorCod(codDestino);
            Destino localDestino = buscarDestinoPorCod(codOrigem);
            TipoCarga tipoCargaObj = buscarTipoCargaPorNumero(numeroTipo);
            Cliente cliente = buscarClientePorCod(codCliente);

            if(localOrigem == null){
            try{  
                throw new IllegalArgumentException(" Erro: Local de origem do código fornecido não está cadastrado.");
            }catch (IllegalArgumentException e) {
                return e.getMessage();
            }
            }else if(localDestino == null){
            try{
                throw new IllegalArgumentException(" Erro: Local de destino do código fornecido não está cadastrado.");
            }catch (IllegalArgumentException e) {
                return e.getMessage();
            }
            }else if(tipoCargaObj == null){
            try{
                throw new IllegalArgumentException(" Erro: Tipo de carga do código fornecido não está cadastrado.");
            }catch (IllegalArgumentException e) {
                return e.getMessage();
            }
            }else if(cliente == null){
            try{
                throw new IllegalArgumentException(" Erro: Cliente do código fornecido não está cadastrado.");
            }catch (IllegalArgumentException e) {
                return e.getMessage();
            }
            }else{
                    Carga novaCarga = new Carga(codCarga, peso, valorDeclarado, tempoMaximo); //sem situacao
                    if(distancia > 0){
                        novaCarga.setDistancia(distancia);
                    }
                    novaCarga.setDestino(localDestino);
                    novaCarga.setLocalOrigem(localOrigem);
                    novaCarga.setTipoCarga(tipoCargaObj);
                    novaCarga.setCliente(cliente);

                    //cadastroCargas.add(novaCarga);
                    cargasPendentes.add(novaCarga);
                    cadastroCargas.add(novaCarga);
                    cadastroCargas.sort(Comparator.comparingInt(c -> c.getcodCarga()));
                    cargasPendentes.sort(Comparator.comparingInt(c -> c.getcodCarga()));
                    
                    System.out.println(novaCarga.toString());
                    cadastrarCargaEmCliente(codCarga, codCliente);
                    return "Cadastro realizado e carga foi vinculada ao cliente.";
                
            }
        }

    public String cadastrarDestino(int codDestino, String nome, String cidade){
            if(buscaCadDestino(codDestino) != -1){
            try{
                throw new IllegalArgumentException(" Erro: Já existe um destino com o código de destino indicado.");
            }catch (IllegalArgumentException e) {
                return e.getMessage();
            }
            }else{

                Destino novoDestino = new Destino(codDestino, nome, cidade);
                cadastroDestinos.add(novoDestino);
                cadastroDestinos.sort(Comparator.comparingInt(c -> c.getcodDestino()));

                System.out.println(toStringDestino(codDestino));
                return "Cadastro realizado.";
            }
    }

    public String cadastrarPerecivel(int numero, String descricao, String origem, int tempoMaxValidade){
            if(buscaCadTipoCarga(numero) != -1){
            try{
                 throw new IllegalArgumentException (" Erro: Já existe um tipo de carga perecível com o nome indicado.");
            }catch (IllegalArgumentException e) {
                return e.getMessage();
            }
            }else{

            Perecivel novoPerecivel = new Perecivel(numero, descricao, origem, tempoMaxValidade);
            cadastroTipoCarga.add(novoPerecivel);
            cadastroTipoCarga.sort(Comparator.comparingInt(c -> c.getNumero()));
            System.out.println(toStringTipoCarga(numero));
            return "Cadastro realizado.";
            }
    }

    public String cadastrarDuravel(int numero, String descricao, String setor, String materiaPrincipal){
            if(buscaCadTipoCarga(numero) != -1){
            try{
                 throw new IllegalArgumentException (" Erro: Já existe um tipo de carga durável com o nome indicado.");
            }catch (IllegalArgumentException e) {
                return e.getMessage();
            }
            }else{

            Duravel novoDuravel = new Duravel(numero, descricao, setor, materiaPrincipal);
            cadastroTipoCarga.add(novoDuravel);
            cadastroTipoCarga.sort(Comparator.comparingInt(c -> c.getNumero()));
            System.out.println(toStringTipoCarga(numero));
            return "Cadastro realizado.";
            }
    }

    /*public void cadastrarDefineSitCC(){
        Carga carga = buscarCargaPorCod(codCarga);

        if (carga != null) {
        define.addPedido(carga);
        }else{
        throw new IllegalArgumentException ("Erro: Carga ou caminhão não encontrados para os códigos fornecidos.");

        }
    }
    */

    // AUX DEFINESITCC =========================================================================================================
    public void auxAddCaminhao(int codCaminhao){
        Caminhao caminhao = buscarCaminhaoPorCod(codCaminhao);
        if(caminhao != null){
        define.addCaminhao(caminhao);
        }
    }

    public void auxAddPedido(int codCarga){
        Carga carga = buscarCargaPorCod(codCarga);
        if(carga != null){
        define.addPedido(carga);
        }

        if(carga.getSituacao().equalsIgnoreCase("PENDENTE")){
        }else{
            cargasPendentes.remove(carga);
        }
    }

    public void auxAlteraSit(int codCarga, String situacao){
        Carga carga = buscarCargaPorCod(codCarga);
        if(carga != null){
        define.alteraSituacaoCarga(carga, situacao);
        }

    }
    
    public void removeCarga(){ //para remover carga de DefineSitCC e de Cadastro
        Carga cargaRem = define.getCargaRemovida();
        cadastroCargas.remove(cargaRem);
        cargasPendentes.remove(cargaRem);
    }

    public void auxBuscaPedidoString(int codCarga, int codCaminhao){
        Carga carga = buscarCargaPorCod(codCarga);
        Caminhao caminhao = buscarCaminhaoPorCod(codCaminhao);
        if(carga != null && caminhao != null){
        define.buscaPedidoString(carga, caminhao);
        }
    }

    //MÉTODOS ===================================================================================================================

    //CAMINHÃO -------------------------------------------------------------
    public int buscaCadCaminhao(String nome){
        for (int i = 0; i < cadastroCaminhoes.size(); i++) {
            if(cadastroCaminhoes.get(i).getNome().equals(nome)){
                //caminhão está cadastrado
                return i;

            }
        }
        return -1;

    }

    public Caminhao buscarCaminhaoPorCod(int codCaminhao){
        for(int i=0; i < cadastroCaminhoes.size(); i++){
            Caminhao caminhao = cadastroCaminhoes.get(i);

            if(caminhao.getcodCaminhao() == codCaminhao){
                return caminhao;
            }
        }

        try{
        throw new IllegalArgumentException (" Erro: Caminhão não encontrado para o código fornecido.");
        }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            return null;
    }

    //CLIENTES --------------------------------------------------------------
    public int buscaCadCliente(int codCliente){
        for (int i = 0; i < cadastroClientes.size(); i++) {
            if(cadastroClientes.get(i).getcodCliente() == codCliente){
                //cliente está cadastrado
                return i;

            }
        }

        return -1;

    }

    public Cliente buscarClientePorCod(int codCliente){
        for(int i=0; i < cadastroClientes.size(); i++){
            Cliente cliente = cadastroClientes.get(i);

            if(cliente.getcodCliente() == codCliente){
                return cliente;
            }
        }

        try{
        throw new IllegalArgumentException (" Erro: Cliente não encontrados para o código fornecido.");
        }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        return null;

    }

    //CARGAS ----------------------------------------------------------------
    public Carga buscarCargaPorCod(int codCarga){
        for(int i = 0; i < cadastroCargas.size();i++){
            Carga carga = cadastroCargas.get(i);

            if(carga.getcodCarga() == codCarga){
                return carga;

            }
        }
        try{
        throw new IllegalArgumentException (" Erro: Carga não encontrada para o código fornecido.");
        }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
        }
        return null;

    }

    public int buscaCadCarga(int codCarga){
        for (int i = 0; i < cadastroCargas.size(); i++) {
            if(cadastroCargas.get(i).getcodCarga() == codCarga){
                return i;

            }
        }
        return -1;

    }

    //DESTINOS ---------------------------------------------------------------
    public int buscaCadDestino(int codDestino){
        for (int i = 0; i < cadastroDestinos.size(); i++) {
            if(cadastroDestinos.get(i).getcodDestino() == codDestino){
                return i;

            }
        }
        return -1;

    }

    public Destino buscarDestinoPorCod(int codDestino){
        for(int i=0; i < cadastroDestinos.size(); i++){
            Destino destino = cadastroDestinos.get(i);

            if(destino.getcodDestino() == codDestino){
                return destino;
            }
        }
        try{
        throw new IllegalArgumentException (" Erro: Destino não encontrado para o código fornecido.");
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    //TIPO CARGA -------------------------------------------------------------
    public int buscaCadTipoCarga(int numero){
        for (int i = 0; i < cadastroTipoCarga.size(); i++) {
            if(cadastroTipoCarga.get(i).getNumero() == numero){
                return i;

            }
        }
        return -1;

    }

    public TipoCarga buscarTipoCargaPorNumero(int numeroTipo) {
        for (int i = 0; i < cadastroTipoCarga.size(); i++) {
            TipoCarga tipoCarga = cadastroTipoCarga.get(i);

            if (tipoCarga.getNumero() == numeroTipo) {
                return tipoCarga;

            }
        }
        try{
        throw new IllegalArgumentException (" Erro: Tipo de carga não encontrada para o código fornecido.");
        }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
        }
        return null;

    }


    //TO STRING =============================================================================================================================
    public String toStringCaminhao(String nome){
        int i = buscaCadCaminhao(nome);

            if(i != -1){
                //toString de caminhão cadastrado
                return cadastroCaminhoes.get(i).toString();
            }
            try{
                throw new IllegalArgumentException(" Erro: Caminhão não está cadastrado.");
            } catch (IllegalArgumentException e) {
            return e.getMessage();
            }
    }
    

    public String toStringCliente(int codCliente){
            int i = buscaCadCliente(codCliente);
            if(i != -1){
                //toString de cliente cadastrado
                return cadastroClientes.get(i).toString();

            }
            try{
            throw new IllegalArgumentException(" Erro: Cliente não está cadastrado.");
        } catch (IllegalArgumentException e) {
            return e.getMessage();
            }
    }

    public String toStringCarga(int codCarga){
            int i = buscaCadCarga(codCarga);
            if(i != -1){
                return cadastroCargas.get(i).toString();

            }
            try{
            throw new IllegalArgumentException(" Erro: Carga não está cadastrada.");
            } catch (IllegalArgumentException e) {
            return e.getMessage();
            }
    }

    public String toStringDestino(int codDestino){
            int i = buscaCadDestino(codDestino);
            if(i != -1){
                return cadastroDestinos.get(i).toString();

            }
            try{
            throw new IllegalArgumentException(" Erro: Destino não está cadastrado.");
                } catch (IllegalArgumentException e) {
            return e.getMessage();
            }
    }

    public String toStringTipoCarga(int numero) {
            int i = buscaCadTipoCarga(numero);
            TipoCarga tipo = buscarTipoCargaPorNumero(numero);
    
            if (i != -1) {
                return cadastroTipoCarga.get(i).toString();  // Certifique-se de chamar toString() aqui
            }
            try{
            throw new IllegalArgumentException(" Erro: Tipo de carga não está cadastrado.");
            } catch (IllegalArgumentException e) {
            return e.getMessage();
            }
    }

    public String toStringListaCaminhoes() {
        return cadastroCaminhoes.stream()
                .map(Caminhao::toString)
                .collect(Collectors.joining("\n"));
    }

    public String toStringListaClientes() {
        return cadastroClientes.stream()
                .map(Cliente::toString)
                .collect(Collectors.joining("\n"));
    }

    public String toStringListaDestinos() {
        return cadastroDestinos.stream()
                .map(Destino::toString)
                .collect(Collectors.joining("\n"));
    }

    public String toStringListaTipoCarga() {
        return cadastroTipoCarga.stream()
                .map(TipoCarga::toString)
                .collect(Collectors.joining("\n"));
    }

    public String toStringListaCargas() {
        return cadastroCargas.stream()
                .map(Carga::toString)
                .collect(Collectors.joining("\n"));
    }

    public String toStringListaCargasPendentes() {
        return cargasPendentes.stream()
                .map(Carga::toString)
                .collect(Collectors.joining("\n"));
    }

    
public String toStringCodigos() {

    String codigosDestino = cadastroDestinos.stream()
            .map(destino -> String.valueOf(destino.getcodDestino()))
            .collect(Collectors.joining(" "));

    String codigosClientes = cadastroClientes.stream()
            .map(cliente -> String.valueOf(cliente.getcodCliente()))
            .collect(Collectors.joining(" "));

    String numerosTipoCarga = cadastroTipoCarga.stream()
            .map(tipoCarga -> String.valueOf(tipoCarga.getNumero()))
            .collect(Collectors.joining(" "));

    return "Códigos de Destino: " + codigosDestino + "\n" +
           "Códigos de Clientes: " + codigosClientes + "\n" +
           "Números dos Tipos de Carga: " + numerosTipoCarga;
}



    //fazer um tamém para printar cargasPendentes
}