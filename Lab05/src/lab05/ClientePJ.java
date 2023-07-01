package lab05;

import lab05.exceptions.CadastroException;

import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private LocalDate dataFundacao;
    private final ArrayList<Frota> listaFrotas;
    private int qtdeFuncionarios;

    public ClientePJ(String nome, String cnpj, String telefone, String endereco, String email, LocalDate dataFundacao, int qtdeFuncionarios) {
        super(nome, telefone, endereco, email);
        if (!Validacao.validarCNPJ(cnpj)) {
            throw new CadastroException("O CNPJ cadastrado é inválido!");
        }

        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
        listaFrotas = new ArrayList<>();
    }

    public String getCnpj() {
        return cnpj;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    public ArrayList<Frota> getFrotas() {
        return listaFrotas;
    }

    public boolean cadastrarFrotas(Frota frota) {
        if (listaFrotas.contains(frota)) {
            return false;
        }

        listaFrotas.add(frota);
        return true;
    }

    public boolean removeFrotas(Frota frota) {
        if (!listaFrotas.contains(frota)) {
            return false;
        }

        listaFrotas.remove(frota);
        return true;
    }

    public boolean atualizarFrota(Frota[] frotas) {
        if (frotas == null)
            return false;

        for (Frota frota : frotas) {
            if (frota == null)
                return false;
        }

        listaFrotas.clear();
        for (Frota frota : frotas) {
            listaFrotas.add(frota);
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", getNome(), getCnpj());
    }
}
