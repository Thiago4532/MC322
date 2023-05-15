import java.time.LocalDate;
import java.util.Date;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private LocalDate dataFundacao;
    private int qtdeFuncionarios;

    public ClientePJ(String nome, String endereco, String CNPJ, LocalDate dataFundacao, int qtdeFuncionarios) {
        super(nome, endereco);
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;

        if (!Validacao.validarCNPJ(CNPJ))
            throw new IllegalArgumentException("CNPJ inválido!");
        this.CNPJ = CNPJ;
    }

    public String getCNPJ() {
        return CNPJ;
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

    @Override
    public double calculaScore() {
        return (1.0 + (qtdeFuncionarios)/100.0) * getListaVeiculos().size();
    }

    @Override
    public String toString() {
        return String.format("ClientePJ { nome: %s, endereco: %s, CNPJ: %s, dataFundacao: %s }",
            getNome(), getEndereco(), CNPJ, dataFundacao);
    }


}
