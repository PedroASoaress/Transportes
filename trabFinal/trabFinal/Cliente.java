package trabFinal;
import java.util.ArrayList;

public class Cliente{
    private int codCliente;
    private String nome;
    private int telefone;
    private ArrayList<Carga> listaCarga;

    public Cliente(int codCliente, String nome, int telefone) {
        this.nome = nome;
        this.codCliente = codCliente;
        this.telefone = telefone;
        listaCarga = new ArrayList<>();
        
    }

    //CARGA

    //inicio getters e setters
    public int getcodCliente() {
        return codCliente;
    }

    public void setcodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
    //fim getters e setters

    public void addCarga(Carga carga){
        listaCarga.add(carga);
    }

    //usar lambda para mostrar dados de todas as cargas registradas em cliente

    @Override
    public String toString() {
        return "Cliente [codCliente=" + codCliente + ", nome=" + nome + ", telefone=" + telefone + ", cargas registradas=" +  listaCarga.size() +"]";
    }


}
