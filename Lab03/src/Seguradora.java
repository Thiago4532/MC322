import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Sinistro> listaSinistros;
    private List<Cliente> listaClientes;

    // Construtores
    public Seguradora(String nome, String telefone, String email, String endereco, List<Sinistro> listaSinistros,
                      List<Cliente> listaClientes) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = listaSinistros;
        this.listaClientes = listaClientes;
    }

    public Seguradora(String nome, String telefone, String email, String endereco) {
        this(nome, telefone, email, endereco, new ArrayList<>(), new ArrayList<>());
    }

    // Getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean cadastrarCliente(Cliente cliente) {
        if (listaClientes.contains(cliente)) {
            return false;
        } else {
            listaClientes.add(cliente);
            return true;
        }
    }

    public boolean removerCliente(Cliente cliente) {
        if (!listaClientes.contains(cliente))
            return false;

        ArrayList<Sinistro> sinistros = new ArrayList<>();
        for (Sinistro s : listaSinistros) {
            if (s.getCliente().equals(cliente)) { // Comparar pela memória, não pelo conteúdo.
                sinistros.add(s);
            }
        }

        for (Sinistro s : sinistros) {
            listaSinistros.remove(s);
        }
        listaClientes.remove(cliente);
        return true;
    }

    public void listarClientes(String prefix) {
        for (Cliente c : listaClientes) {
            if (c instanceof ClientePJ)
            System.out.println(prefix + c);
        }
    }
    public void listarClientes() { listarClientes(""); }

    public Cliente[] copiaListaClientes() {
        return listaClientes.toArray(new Cliente[0]);
    }

    public boolean gerarSinistro(String data, String endereco, Cliente cliente, Veiculo veiculo) {
        boolean achou_cliente = false;
        for (Cliente c : listaClientes) {
            if (cliente == c) { // Comparar pela memória, não pelo conteúdo.
                achou_cliente = true;
                break;
            }
        }
        if (!achou_cliente)
            return false; // Cliente não é da seguradora

        boolean achou_veiculo = false;
        for (Veiculo v : cliente.copiaListaVeiculos()) {
            if (veiculo == v) { // Comparar pela memória, não pelo conteúdo
                achou_veiculo = true;
                break;
            }
        }

        if (!achou_veiculo)
            return false; // Veiculo não é do cliente

        listaSinistros.add(new Sinistro(data, endereco, this, veiculo, cliente));
        return true;
    }

    public void listarSinitros(String prefix) {
        for (Sinistro s : listaSinistros) {
            System.out.println(prefix + s);
        }
    }
    public void listarSinitros() { listarSinitros("");}

    public boolean visualizarSinistro(Cliente cliente, String prefix) {
        for (Sinistro s : listaSinistros) {
            if (s.getCliente() == cliente) { // Comparar pela memória, não pelo conteúdo
                System.out.println(prefix + s);
                return true;
            }
        }
        return false;
    }
    public boolean visualizarSinistro(Cliente cliente) { return visualizarSinistro(cliente, ""); }

    @Override
    public String toString() {
        return String.format("Seguradora { nome: %s, telefone: %s, email: %s, endereco: %s }",
                nome, telefone, email, endereco);
    }
}