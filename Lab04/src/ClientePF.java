import java.time.LocalDate;

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

        if (!Validacao.validarCPF(CPF))
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

    public int getIdade() {
        LocalDate date = LocalDate.now();
        int idade = date.getYear() - dataNascimento.getYear();
        if (date.getMonthValue() == dataNascimento.getMonthValue()) {
            if (date.getDayOfYear() < dataNascimento.getDayOfYear())
                idade--;
        } else if (date.getMonthValue() < dataNascimento.getMonthValue())
            idade--;

        return idade;
    }

    @Override
    public double calculaScore() {
        double fator_idade;
        int idade = getIdade();

        if (idade >= 18 && idade <= 30)
            fator_idade = CalcSeguro.FATOR_18_30.value();
        else if (idade >= 30 && idade <= 60)
            fator_idade = CalcSeguro.FATOR_30_60.value();
        else if (idade >= 60 && idade <= 90)
            fator_idade = CalcSeguro.FATOR_60_90.value();
        else
            throw new RuntimeException("unreachable: idade invalida");

        return CalcSeguro.VALOR_BASE.value() * fator_idade * getListaVeiculos().size();
    }

    @Override
    public String toString() {
        return String.format("ClientePF { nome: %s, endereco: %s, CPF: %s, genero: %s, dataLicenca: %s, educacao: %s, dataNascimento: %s, classeEconomica: %s }",
                getNome(), getEndereco(), CPF, genero, dataLicenca, educacao, dataNascimento, classeEconomica);
    }

    // Funções Estáticas

}
