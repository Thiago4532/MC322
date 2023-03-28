import java.time.LocalDateTime;

public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    // Construtor
    public Cliente(String nome, String cpf, String dataNascimento, String endereco) {
        this.nome = nome;
        this.setCpf(cpf);
        this.dataNascimento = dataNascimento;
        this.idade = calcularIdade(dataNascimento);
        this.endereco = endereco;
    }

    // Getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (validarCPF(cpf)) {
            this.cpf = cpf;
        } else {
            // Eu deveria dá throw em uma exceção msm? Ou existe uma maneira menos agressiva?
            throw new IllegalArgumentException("CPF inválido!");
        }
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
        this.idade = calcularIdade(dataNascimento);
    }

    public int getIdade() {
        return idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Funções Estáticas
    private static boolean validarCPF(String cpf) {
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

    private static int calcularIdade(String dataNascimento) {
        // Assumindo o padrão de data DD/MM/YY

        if (!dataNascimento.matches("[0-9]+/[0-9]+/[0-9]+"))
            throw new IllegalArgumentException("Data de nascimento inválida!");

        int[] data = Util.arrayStringToInt(dataNascimento.split("/"));
        int diaNascimento = data[0];
        int mesNascimento = data[1];
        int anoNascimento = data[2];

        LocalDateTime atual = LocalDateTime.now();
        int diaAtual = atual.getDayOfMonth();
        int mesAtual = atual.getMonth().getValue();
        int anoAtual = atual.getYear();

        int idade = anoAtual - anoNascimento;
        if (mesAtual < mesNascimento ||
            (mesAtual == mesNascimento && diaAtual < diaNascimento))
            idade--;

        return idade;
    }
}
