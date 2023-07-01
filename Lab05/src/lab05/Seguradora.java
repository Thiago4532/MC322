package lab05;

import lab05.exceptions.CadastroException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Seguradora {
    private String nome;
    private final String cnpj;
    private String telefone;
    private String endereco;
    private String email;
    private final ArrayList<Cliente> listaClientes;
    private final ArrayList<Seguro> listaSeguros;

    public Seguradora(String nome, String cnpj, String telefone, String endereco, String email) {
        if (!Validacao.validarCNPJ(cnpj)) {
            throw new CadastroException("O CNPJ cadastrado é inválido!");
        }
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        listaClientes = new ArrayList<>();
        listaSeguros = new ArrayList<>();
    }

    public String getCnpj() {
        return cnpj;
    }

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente) {
        ArrayList<Seguro> seguros = new ArrayList<>();
        for (Seguro seguro : listaSeguros) {
            if (seguro.getCliente().equals(cliente)) {
                seguros.add(seguro);
            }
        }
        return seguros;
    }

    public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente) {
        ArrayList<Sinistro> sinistros = new ArrayList<>();
        for (Seguro seguro : listaSeguros) {
            if (seguro.getCliente().equals(cliente)) {
                sinistros.addAll(seguro.getSinistros());
            }
        }
        return sinistros;
    }

    public SeguroPF gerarSeguro(ClientePF cliente, Veiculo veiculo, LocalDate dataInicio, LocalDate dataFim) {
        // TODO: Conferir se dataInicio > dataFim
        SeguroPF seguro = new SeguroPF(cliente, veiculo, dataInicio, dataFim, this);
        listaSeguros.add(seguro);
        return seguro;
    }

    public SeguroPJ gerarSeguro(ClientePJ cliente, Frota frota, LocalDate dataInicio, LocalDate dataFim) {
        // TODO: Conferir se dataInicio > dataFim
        SeguroPJ seguro = new SeguroPJ(cliente, frota, dataInicio, dataFim, this);
        listaSeguros.add(seguro);
        return seguro;
    }

    public boolean cancelarSeguro(Seguro seguro) {
        return listaSeguros.remove(seguro);
    }

    public boolean cadastrarCliente(Cliente cliente) {
        return listaClientes.add(cliente);
    }

    public boolean removerCliente(Cliente cliente) {
        if (!listaClientes.contains(cliente)) {
            return false;
        }

        ArrayList<Seguro> seguros = getSegurosPorCliente(cliente);
        for (Seguro seguro : seguros) {
            cancelarSeguro(seguro);
        }

        listaClientes.remove(cliente);
        return true;
    }

    public double calcularReceita() {
        double receita = 0;
        for (Seguro seguro : listaSeguros) {
            receita += seguro.getValorMensal();
        }
        return receita;
    }

    @Override
    public String toString() {
        return "Seguradora{" +
                "cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
