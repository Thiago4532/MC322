package lab05;

import lab05.exceptions.CadastroException;
import lab05.utils.DateParser;

import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private String educacao;
    private LocalDate dataNascimento;
    private final ArrayList<Veiculo> listaVeiculos;

    public ClientePF(String nome, String cpf, String telefone, String endereco, String email, String genero, String educacao, LocalDate dataNascimento) {
        super(nome, telefone, endereco, email);
        if (!Validacao.validarCPF(cpf)) {
            throw new CadastroException("O CPF cadastrado é inválido!");
        }
        if (!Validacao.validaIdade(dataNascimento)) {
            throw new CadastroException("A idade do cliente deve ser maior que 18 anos e menor que 90 anos!");
        }

        this.cpf = cpf;
        this.genero = genero;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.listaVeiculos = new ArrayList<>();
    }

    public String getCpf() {
        return cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return listaVeiculos;
    }

    public int getIdade() {
        int idade = LocalDate.now().getYear() - dataNascimento.getYear();
        if (LocalDate.now().getMonthValue() < dataNascimento.getMonthValue()) {
            idade--;
        } else if (LocalDate.now().getMonthValue() == dataNascimento.getMonthValue()) {
            if (LocalDate.now().getDayOfMonth() < dataNascimento.getDayOfMonth()) {
                idade--;
            }
        }

        return idade;
    }

    public boolean cadastrarVeiculo(Veiculo veiculo) {
        if (listaVeiculos.contains(veiculo)) {
            return false;
        }

        listaVeiculos.add(veiculo);
        return true;
    }

    public boolean removerVeiculo(Veiculo veiculo) {
        if (!listaVeiculos.contains(veiculo)) {
            return false;
        }

        listaVeiculos.remove(veiculo);
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", getNome(), getCpf());
    }
}
