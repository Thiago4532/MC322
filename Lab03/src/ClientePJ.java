import java.time.LocalDate;
import java.util.Date;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private LocalDate dataFundacao;

    public ClientePJ(String nome, String endereco, String CNPJ, LocalDate dataFundacao) {
        super(nome, endereco);
        this.dataFundacao = dataFundacao;

        if (!validarCNPJ(CNPJ))
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

    @Override
    public String toString() {
        return String.format("ClientePJ { nome: %s, endereco: %s, CNPJ: %s, dataFundacao: %s }",
            getNome(), getEndereco(), CNPJ, dataFundacao);
    }

    // Funções Estáticas

    public static boolean validarCNPJ(String CNPJ) {
        String nCNPJ = CNPJ.replaceAll("[^0-9]", "");
        if (nCNPJ.length() != 14)
            return false;

        int d1 = nCNPJ.charAt(12) - '0';
        int d2 = nCNPJ.charAt(13) - '0';

        int soma = 0;
        for (int i = 0; i < 12; i++) {
            int d = nCNPJ.charAt(i) - '0';
            soma += d * (i < 4 ? 5 - i : 13 - i);
        }

        int digito_1 = soma % 11;
        digito_1 = digito_1 < 2 ? 0 : 11 - digito_1;

        soma = 0;
        for (int i = 0; i < 13; i++) {
            int d = nCNPJ.charAt(i) - '0';
            soma += d * (i < 5 ? 6 - i : 14 - i);
        }

        int digito_2 = soma % 11;
        digito_2 = digito_2 < 2 ? 0 : 11 - digito_2;

        return (digito_1 == d1 && digito_2 == d2);
    }
}
