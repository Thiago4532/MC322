import java.time.LocalDate;
import java.util.Date;

public class ClientePF extends Cliente {
    private final String CPF;
    private String genero;
    private LocalDate dataLicenca;
    private String educacao;
    private LocalDate dataNascimento;
    private String classeEconomica;

    public ClientePF(String nome, String endereco, String CPF, String genero, LocalDate dataLicenca,
                     String educacao, LocalDate dataNascimento, String classeEconomica) {
        super(nome, endereco);
        this.dataNascimento = dataNascimento;
        this.dataLicenca = dataLicenca;
        this.genero = genero;
        this.educacao = educacao;
        this.classeEconomica = classeEconomica;

        if (!validarCPF(CPF))
            throw new IllegalArgumentException("CPF inválido!");
        this.CPF = CPF;
    }

    public String getCPF() {
        return CPF;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataLicenca() {
        return dataLicenca;
    }
    public void setDataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
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

    public String getClasseEconomica() {
        return classeEconomica;
    }
    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    @Override
    public String toString() {
        return String.format("ClientePF { nome: %s, endereco: %s, CPF: %s, genero: %s, dataLicenca: %s, educacao: %s, dataNascimento: %s, classeEconomica: %s }",
                getNome(), getEndereco(), CPF, genero, dataLicenca, educacao, dataNascimento, classeEconomica);
    }

    // Funções Estáticas
    public static boolean validarCPF(String cpf) {
        String nCpf = cpf.replaceAll("[^0-9]", "");

        if (nCpf.length() != 11)
            return false;

        if (nCpf.chars().distinct().count() == 1)
            return false;

        int v1 = 0, v2 = 0;
        for (int i = 0; i < 9; i++) {
            int d = nCpf.charAt(i) - '0';
            int j = 9 - i;

            v1 += d * (10 - i);
            v2 += d * (11 - i);
        }
        v1 = 11 - (v1 % 11);
        if (v1 > 9)
            v1 = 0;

        v2 += v1 * 2;
        v2 = 11 - (v2 % 11);
        if (v2 > 9)
            v2 = 0;

        int t_v1 = nCpf.charAt(9) - '0';
        int t_v2 = nCpf.charAt(10) - '0';
        return (v1 == t_v1 && v2 == t_v2);
    }
}
