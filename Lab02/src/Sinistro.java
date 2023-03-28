import java.util.UUID;

public class Sinistro {
    private int id;
    private String data;
    private String endereco;

    // Construtor
    public Sinistro(String data, String endereco) {
        this.id = genId();
        this.data = data;
        this.endereco = endereco;
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

    // Funções Estáticas
    private static int genId() {
        // Usar esse método gera um ID positivo, usar a funçao randomUUID utiliza números aleatórios,
        // seguros, embora pra esse problema isso não faça diferença.
        return Math.abs(UUID.randomUUID().hashCode());
    }
}
