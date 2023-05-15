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
        cliente.setValorSeguro(cliente.getValorSeguro() - calculaPrecoSeguroCliente(cliente));

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

        cliente.setValorSeguro(cliente.getValorSeguro() - calculaPrecoSeguroCliente(cliente));
        listaSinistros.add(new Sinistro(data, endereco, this, veiculo, cliente));
        cliente.setValorSeguro(cliente.getValorSeguro() + calculaPrecoSeguroCliente(cliente));
        return true;
    }

    public void listarSinitros(String prefix) {
        for (Sinistro s : listaSinistros) {
            System.out.println(prefix + s);
        }
    }
    public void listarSinitros() { listarSinitros("");}

    public boolean visualizarSinistro(Cliente cliente, String prefix) {
        boolean good = false;
        for (Sinistro s : listaSinistros) {
            if (s.getCliente() == cliente) { // Comparar pela memória, não pelo conteúdo
                System.out.println(prefix + s);
                good = true;
            }
        }
        return good;
    }
    public boolean visualizarSinistro(Cliente cliente) { return visualizarSinistro(cliente, ""); }

    public boolean clienteFazParte(Cliente cliente) {
        for (Cliente c : listaClientes) {
            if (c == cliente) { // Comparar pela memória, não pelo conteúdo
                return true;
            }
        }

        return false;
    }

    public int quantidadeSinistros(Cliente cliente) {
        if (!clienteFazParte(cliente))
            return -1; // Retorna -1 se o cliente não fizer parte da seguradora

        int qtd = 0;
        for (Sinistro s : listaSinistros) {
            if (s.getCliente() == cliente) { // Comparar pela memória, não pelo conteúdo
                qtd++;
            }
        }

        return qtd;
    }

    public double calculaPrecoSeguroCliente(Cliente cliente) {
        if (!clienteFazParte(cliente))
            return 0.0; // Retorna 0 se o cliente não faz parte da seguradora

        return cliente.calculaScore() * (1.0 + quantidadeSinistros(cliente));
    }

    public double calcularReceita() {
        double sum = 0.0;
        for (Cliente cliente : listaClientes) {
            // Recalculando esse preço, já que precisamos apenas do preço do cliente nessa seguradora, e não em todas.
            sum += calculaPrecoSeguroCliente(cliente);
        }

        return sum;
    }

    public boolean transferirSeguro(Cliente cliente1, Cliente cliente2) {
        if (!clienteFazParte(cliente1) || !clienteFazParte(cliente2))
            return false;

        cliente1.setValorSeguro(cliente1.getValorSeguro() - calculaPrecoSeguroCliente(cliente1));
        cliente2.setValorSeguro(cliente2.getValorSeguro() - calculaPrecoSeguroCliente(cliente2));

        for (Sinistro s : listaSinistros) {
            if (s.getCliente() == cliente1) { // Comparar pela memória, não pelo conteúdo
                s.setCliente(cliente2);
            }
        }

        cliente1.setValorSeguro(cliente1.getValorSeguro() + calculaPrecoSeguroCliente(cliente1));
        cliente2.setValorSeguro(cliente2.getValorSeguro() + calculaPrecoSeguroCliente(cliente2));
        return true;
    }

    @Override
    public String toString() {
        return String.format("Seguradora { nome: %s, telefone: %s, email: %s, endereco: %s }",
                nome, telefone, email, endereco);
    }
}