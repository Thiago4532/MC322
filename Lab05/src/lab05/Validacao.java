package lab05;

import java.time.LocalDate;

public class Validacao {
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

    public static boolean validaIdade(int idade) {
        return idade >= 18 && idade <= 90;
    }

    public static boolean validaIdade(LocalDate dataNascimento) {
        LocalDate date = LocalDate.now();
        int idade = date.getYear() - dataNascimento.getYear();
        if (date.getMonthValue() == dataNascimento.getMonthValue()) {
            if (date.getDayOfYear() < dataNascimento.getDayOfYear())
                idade--;
        } else if (date.getMonthValue() < dataNascimento.getMonthValue())
            idade--;

        return validaIdade(idade);
    }
}
