package trabFinal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class TesteMain {
    public static void main(String args[]){

        Scanner input = new Scanner(System.in);
        boolean loop = true;
        Cadastros cad = new Cadastros();
        int i1, i2, i3, i4, i5, i6;
        String n1, n2, n3;
        double d1, d2, d3;
        
        cad.cadastrarCaminhao("Poo", 33,445, 33, 333);
        cad.cadastrarDestino(99, "casa", "paris");
        cad.cadastrarDestino(888, "fazenda", "londres");
        cad.cadastrarDuravel(87, "barra de ferro", "bb", "metal");
        cad.cadastrarPerecivel(45, "pão", "roma", 99);
        cad.cadastrarCliente(999, "Cláudia", 222);
        cad.cadastrarCarga(22, 12, 22, 22, 45, 99, 888, 12, 999);

        do{
          System.out.println("\nPágina inicial.\n [1] Novo cadastro.\n [2] Pedidos.\n [3] Consultar cadastros.\n [4] Dados. \n [5] Sair.");
          int selecao = input.nextInt();
          if(selecao == 5){
            loop = false;
          }

          switch (selecao) {
            case 1: //CADASTROS -------------------------------------------------------------------------------------
            int selecaoCad;

            System.out.println("\nCadastros. \n [1] Cliente.\n [2] Destino. \n [3] Caminhão.\n [4] Tipo de Carga.\n [5] Carga.\n [6] Voltar para página inicial.");
            selecaoCad = input.nextInt();

            if(selecaoCad == 6){
              System.out.println("\nRetornando para a página inicial...");
              break;
            }

             switch (selecaoCad) {
                  case 1:
                  System.out.println("Insira o código do cliente, o nome e o telefone");
                    i1 = input.nextInt();
                    input.nextLine();
                    n1 = input.nextLine();
                    i2 = input.nextInt();
                  
                  System.out.println(cad.cadastrarCliente(i1,n1,i2));

                    break;

                  case 2:
                  System.out.println("Insira o código do destino, o nome do local e o nome da cidade");
                    i1 = input.nextInt();
                    input.nextLine();
                    n1 = input.nextLine();
                    n2 = input.nextLine();

                  System.out.println(cad.cadastrarDestino(i1, n1, n2));

                  break;

                  case 3:
                  System.out.println("Insira o nome, a velocidade, a autonomia, o custo por Km e o código do caminhao.");
                  input.nextLine();
                    n1 = input.nextLine();
                    d1 = input.nextDouble();
                    d2 = input.nextDouble();
                    d3 = input.nextDouble();
                    i1 = input.nextInt();

                  System.out.println(cad.cadastrarCaminhao(n1,d1,d2,d3,i1));

                  break;

                  case 4:
                  System.out.println("\nTipo de carga. \n [1] Perecível.\n [2] Durável");
                  int selTipo = input.nextInt();
                  if(selTipo==1){
                    System.out.println("Insira o numero, a descricao, a origem e o tempo máximo de validade em dias");
                    i1 = input.nextInt();
                    input.nextLine();
                    n1 = input.nextLine();
                    n2 = input.nextLine();
                    i2 = input.nextInt();

                    
                    System.out.println(cad.cadastrarPerecivel(i1, n1, n2, i2));

                  }else if(selTipo==2){
                    System.out.println("Insira o numero, a descricao, o setor e o material principal");
                    i1 = input.nextInt();
                    input.nextLine();
                    n1 = input.nextLine();
                    n2 = input.nextLine();
                    n3 = input.nextLine();

                    
                    System.out.println(cad.cadastrarDuravel(i1, n1, n2, n3));

                  }else{
                        try {
                    throw new IllegalArgumentException("Valor inválido.");
                    }catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                  }
                  }

                  break;

                  case 5:
                  System.out.println("\n --- TABELA CÓDIGOS CADASTRADOS ---\n"+ cad.toStringCodigos() + "\n");
                  System.out.println("\nInsira o código da carga, o peso, o valor declarado, o tempo máximo para o frete em dias, o numero do tipo carga,"
                  + " o código do destino de origem, o código de destino, a distância e o código do cliente.");
                    i1 = input.nextInt();
                    d1 = input.nextDouble();
                    d2 = input.nextDouble();
                    i2 = input.nextInt(); //frete em dias
                    i3 = input.nextInt();
                    i4 = input.nextInt(); //cod origem
                    i5 = input.nextInt();
                    d3 = input.nextDouble();
                    i6 = input.nextInt();

                  System.out.println(cad.cadastrarCarga(i1, d1, d2, i2, i3, i4, i5, d3, i6));

                  break;
            
                  case 6:
                  System.out.println("\nRetornando para a página inicial...");
                  break;

                  default:
                  try {
                  throw new IllegalArgumentException("Valor inválido.");
                  }catch (IllegalArgumentException e) {
                  System.out.println(e.getMessage());
                }
                break;
                }

              break;
            

            case 2: //REALIZAR PEDIDOS ====================================================================================
                System.out.println("\nPedidos. \n [1] Adicionar caminhão. \n [2] Adicionar novo pedido e fretar.\n [3] Alterar situação de carga. \n [4] Busca pedido. \n [5] Voltar para página inicial.  ");
                int sit2 = input.nextInt();
                if(sit2 == 5){
                  System.out.println("\nRetornando para a página inicial...");
                  break;
                }

                  switch (sit2) {
                    case 1:
                    System.out.println("Insira o código do caminhão à ser adicionado: ");
                    i1 = input.nextInt();
                    cad.auxAddCaminhao(i1);
                  
                      break;

                    case 2:
                    System.out.println("Insira o código da carga a ser adicionada a um novo pedido e fretada.");
                    i1 = input.nextInt();
                    cad.auxAddPedido(i1);

                    break;

                    case 3:
                    System.out.println("Insira o código da carga para alterar sua situação e a nova situação (pendente, finalizada, cancelada, não atendida).");
                    i1 = input.nextInt();
                    input.nextLine();
                    n1 = input.nextLine();

                    cad.auxAlteraSit(i1, n1);
                    break;

                    case 4:
                    System.out.println("Insira o código da carga e do caminhão a serem buscados.");
                    i1 = input.nextInt();
                    i2 = input.nextInt();
                    cad.auxBuscaPedidoString(i1, i2);
                    
                    break;

                    default:
                    try {
                      throw new IllegalArgumentException("Valor inválido.");
                    }catch (IllegalArgumentException e) {
                      System.out.println(e.getMessage());
                    }
                      break;
                }
              break;

            case 3: //CONSULTAR CADASTROS =======================================================================================
                System.out.println("\nConsultar cadastros. \n [1] Clientes.\n [2] Destinos.\n [3] Caminhões. \n [4] Tipo de carga. \n [5] Cargas.\n [6] Cargas pendentes. \n [7] Voltar para página inicial. \n");
                int sit3 = input.nextInt();

                if(sit3 == 7){
                  System.out.println("\nRetornando para a página inicial...");
                  break;
                }
                
                switch (sit3) {
                  case 1:
                    System.out.println(cad.toStringListaClientes());
                    break;
                  case 2:
                    System.out.println(cad.toStringListaDestinos());
                  break;
                  case 3:
                    System.out.println(cad.toStringListaCaminhoes());
                  break;
                  case 4:
                    System.out.println(cad.toStringListaTipoCarga());
                  break;
                  case 5:
                    System.out.println(cad.toStringListaCargas());
                  break;
                  case 6:
                    System.out.println(cad.toStringListaCargasPendentes());
                  break;


                  default:
                try {
                  throw new IllegalArgumentException("Valor inválido.");
                  }catch (IllegalArgumentException e) {
                  System.out.println(e.getMessage());
                }
                    break;
                }
                break;


            case 4: //DADOS ======================================================================================
                System. out.println("\nDados. \n [1] Carregar dados iniciais.\n [2] Salvar dados. \n [3] Carregar dados. \n [4] Voltar para página inicial.");
                int sit4 = input.nextInt();
                if(sit4 == 4){
                  break;
                }

                switch (sit4) {
                  case 1:
                  /*ArrayList<Caminhao> cadastroCaminhoes = new ArrayList<>();
                  ArrayList<Destino> cadastroDestinos = new ArrayList<>();
                  ArrayList<TipoCarga> cadastroTipoCarga = new ArrayList<>();
                  ArrayList<Cliente> cadastroClientes = new ArrayList<>();
                  ArrayList<Carga> cadastroCargas = new ArrayList<>();
                  try{
                   BufferedWriter writer = new BufferedWriter(new FileWriter("Carregar Dados Iniciais.csv"));
                   writer.write("Nome, Velocidade, Autonomia, Custo por Km, Código\n");
                   for(Caminhao cam : cadastroCaminhoes){
                    writer.write(cam.getNome()+";"+cam.getVelocidade()+";"+cam.getAutonomia()+";"+cam.getCustoporKm()+";"+cam.getcodCaminhao()+";");
                   }
                   writer.write("CodDestino, Nome, Cidade\n");
                   for(Destino dest : cadastroDestinos){
                    writer.write(dest.getcodDestino()+";"+dest.getNome()+";"+dest.getCidade()+";");
                   }
                    for(TipoCarga tpcarga : cadastroTipoCarga){
                      if(tpcarga instanceof Duravel){
                        writer.write("Número, Descrição, Setor, Material Principal\n");
                        Duravel duravel = (Duravel) tpcarga;
                        writer.write(tpcarga.getNumero()+";"+tpcarga.getDescricao()+";"+duravel.getSetor()+";"+duravel.getMaterialPrincipal());
                    }}
                    for(TipoCarga tpcarga1 : cadastroTipoCarga){
                      if(tpcarga1 instanceof Perecivel){
                        writer.write("Numero, Descrição, Origem, Tempo Máximo de Validade\n");
                        Perecivel perecivel = (Perecivel) tpcarga1;
                        writer.write(tpcarga1.getNumero()+";"+tpcarga1.getDescricao()+";"+perecivel.getOrigem()+";"+perecivel.gettempoMaxValidade()+";");
                      }
                    }
                    for(Cliente clt: cadastroClientes){
                      writer.write("Código Cliente, Nome, Telefone");
                      writer.write(clt.getcodCliente()+";"+clt.getNome()+";"+clt.getTelefone()+";");
                    }
                    for(Carga crg : cadastroCargas){
                      writer.write("Código Carga, Peso, Valor Declarado, Tempo Máximo, Número, Local Origem, Destino, Distância, Código Cliente\n");
                      for(Cliente clte : cadastroClientes){
                      for(TipoCarga tpcarga2 : cadastroTipoCarga){
                      if(tpcarga2 instanceof Perecivel){
                      Perecivel pcl = (Perecivel) tpcarga2;
                      writer.write(crg.getcodCarga()+";"+crg.getPeso()+";"+crg.getValorDeclarado()+";"+crg.getTempoMaximo()+";"
                      +pcl.getNumero()+";"+crg.getLocalOrigem()+";"+crg.getDestino()+";"+crg.getDistancia()+";"+clte.getcodCliente()+";");
                    }
                  }
                }
              }
                    writer.close();
                  }catch(IOException e){
                    e.printStackTrace();
                  }

                  /*try{
                    BufferedReader reader = new BufferedReader(new FileReader("Carregar Dados Iniciais.csv"));
                    String line;
                    while((line = reader.readLine())!=null){
                    String inf[] = line.split(";");
                    String nome = inf[0];
                    int codCaminhao = Integer.parseInt(inf[4]);
                    double velocidade = Double.parseDouble(inf[2]), custoKm = Double.parseDouble(inf[4]), autonomia = Double.parseDouble(inf[3]);
                    cadastroCaminhoes.add(new Caminhao(nome,velocidade,autonomia,custoKm,codCaminhao));
                    }
                    while((line = reader.readLine())!=null){
                      String ing[] = line.split(";");
                      int codDestino = Integer.parseInt(ing[0]);
                      String nome = ing[1];
                      String cidade = ing[2];
                      cadastroDestinos.add(new Destino(codDestino, nome, cidade));
                    }
                    while((line = reader.readLine())!=null){
                      String ina[] = line.split(";");
                      int codDestino = Integer.parseInt(ina[0]);
                      String nome = ina[1];
                      String cidade = ina[2];
                      cadastroDestinos.add(new Destino(codDestino, nome, cidade));
                    }while((line = reader.readLine())!=null){
                      String inh[] = line.split(";");
                      int numero = Integer.parseInt(inh[0]);
                      String descricao = inh[1];
                      String setor = inh[2];
                      String materialPrincipal = inh[3];
                      cadastroTipoCarga(new Duravel(numero,descricao,setor,materialPrincipal));
                    }/*while((line = reader.readLine())!=null){
                      String iny[] = line.split(";");
                    }

                  }catch(IOException ex){
                    ex.printStackTrace();
                  }*/
                    break;
                
                  case 2:
                  /*ArrayList<Carga> cadastroCargas = new ArrayList<>();
                    try{
                
                      BufferedWriter writer = new BufferedWriter(new FileWriter("Dados Salvos.csv"));
                      for(Carga carga : cadastroCargas){
                          writer.write(carga.getSituacao()+","+carga.getDistancia()+","+carga.getPeso()+","+carga.getTempoMaximo()+
                          ","+carga.getValorDeclarado()+","+carga.getcodCarga()+","+carga.getCliente()+","+carga.getLocalOrigem()
                          +","+carga.getDestino()+","+carga.getTipoCarga()+",");

                      }
                      writer.close();
                    }catch(IOException e){
                      e.printStackTrace();
                    }
                    /*try {
                      BufferedReader reader = new BufferedReader(new FileReader("Dados Salvos.csv"));
                      String line;
                      while((line = reader.readLine()) != null){
                        String ind[] = line.split(";");
                        int codCargas = Integer.parseInt(ind[0]);
                        double peso = Double.parseDouble(ind[1]), valorDeclarado = Double.parseDouble(ind[2]), distancia = Double.parseDouble(ind[3]);
                        int tempoMaximo = Integer.parseInt(ind[4]),numeroTipo = Integer.parseInt(ind[5]),codDestino = Integer.parseInt(ind[6]), 
                        codOrigem = Integer.parseInt(ind[7]),codCliente = Integer.parseInt(ind[8]);
                        cadastroCargas.add(new Carga(codCargas, peso, valorDeclarado, tempoMaximo));
                     }
                       reader.close();
                    } catch (IOException e) {
                      e.printStackTrace();
                    }*/
                  break;
                        
                  case 3:
                    /*try{
                      

                    }catch(IOException E){
                      E.printStackTrace();
                    }
                    try {
                      BufferedReader reader = new BufferedReader(new FileReader("Carregar Dados.csv"));
                      String linha;
                      while((linha = reader.readLine()) != null){
                      System.out.println(linha);
                     }
                       reader.close();
                    } catch (IOException e) {
                      e.printStackTrace();
                    }
                    try {
                      BufferedReader reader = new BufferedReader(new FileReader("Carregar Dados.csv"));
                      String linha;
                      while((linha = reader.readLine()) != null){
                      System.out.println(linha);
                     }
                       reader.close();
                    } catch (IOException e) {
                      e.printStackTrace();
                    }
                    try {
                      BufferedReader reader = new BufferedReader(new FileReader("Carregar Dados.csv"));
                      String linha;
                      while((linha = reader.readLine()) != null){
                      System.out.println(linha);
                     }
                       reader.close();
                    } catch (IOException e) {
                      e.printStackTrace();
                    }*/
                  break;
                
                  default:
                    break;
                }

            default:
                try {
                  throw new IllegalArgumentException("Valor inválido.");
                  }catch (IllegalArgumentException e) {
                  System.out.println(e.getMessage());
                }
              break;
          }

        }while(loop);
      }
    }

