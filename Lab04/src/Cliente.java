import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private String nome;
    private String endereco;
    private List<Veiculo> listaVeiculos;

    private double valorSeguro;

    // Construtor
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<>();
        this.valorSeguro = 0.0;
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

    public boolean adicionarVeiculo(Veiculo veiculo) {
        if (listaVeiculos.contains(veiculo)) {
            return false;
        }

        listaVeiculos.add(veiculo);
        return true;
    }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
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

    public abstract double calculaScore();

    @Override
    public String toString() {
        return String.format("Cliente { nome: %s, endereco: %s }", nome, endereco);
    }
}
