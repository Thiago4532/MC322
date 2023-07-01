package lab05;

import lab05.exceptions.CadastroException;
import lab05.utils.DateParser;

import java.time.LocalDate;
import java.util.ArrayList;

public class Condutor {
    private String nome;
    private final String cpf;
    private String telefone;
    private String endereco;
    private String email;
    private LocalDate dataNascimento;
    private final ArrayList<Sinistro> listaSinitros;

    public Condutor(String nome, String cpf, String telefone, String endereco, String email, LocalDate dataNascimento) {
        if (!Validacao.validarCPF(cpf)) {
            throw new CadastroException("O CPF cadastrado é inválido!");
        }
        if (!Validacao.validaIdade(dataNascimento)) {
            throw new CadastroException("A idade do cliente deve ser maior que 18 anos e menor que 90 anos!");
        }

        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.listaSinitros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Sinistro> getListaSinitros() {
        return listaSinitros;
    }

    public Sinistro adicionarSinistro(LocalDate data, String endereco, Seguro seguro) {
        Sinistro sinistro = new Sinistro(data, endereco, this, seguro);

        listaSinitros.add(sinistro);
        return sinistro;
    }

    @Override
    public String toString() {
        return "Condutor{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento.format(DateParser.FORMATTER) +
                '}';
    }
}
