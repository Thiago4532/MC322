import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String endereco;
    private List<Veiculo> listaVeiculos;

    // Construtor
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<>();
    }

    // Getters and setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Veiculo[] copiaListaVeiculos() {
        return listaVeiculos.toArray(new Veiculo[0]);
    }

    public void listarVeiculos(String prefix) {
        for (Veiculo veiculo : listaVeiculos) {
            System.out.println(prefix + veiculo);
        }
    }
    public void listarVeiculos() {
        listarVeiculos("");
    }

    public boolean adicionarVeiculo(Veiculo veiculo) {
        if (listaVeiculos.contains(veiculo)) {
            return false;
        }

        listaVeiculos.add(veiculo);
        return true;
    }

    @Override
    public String toString() {
        return String.format("Cliente { nome: %s, endereco: %s }", nome, endereco);
    }
}
