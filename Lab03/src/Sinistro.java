import java.util.UUID;

public class Sinistro {
    private final int id;
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    // Construtor
    public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.id = genId();
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    // Funções Estáticas
    private static int genId() {
        // Usar esse método gera um ID positivo, usar a funçao randomUUID utiliza números aleatórios,
        // seguros, embora pra esse problema isso não faça diferença.
        return Math.abs(UUID.randomUUID().hashCode());
    }

    @Override
    public String toString() {
        return String.format("Sinistro { id: %d, data: %s, endereco: %s, seguradora: %s, veiculo: %s, cliente: %s }",
                id, data, endereco, seguradora, veiculo.getPlaca(), cliente.getNome());
    }
}
