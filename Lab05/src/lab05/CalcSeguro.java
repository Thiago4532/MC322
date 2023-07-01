package lab05;

import lab05.exceptions.CadastroException;

public enum CalcSeguro {
    VALOR_BASE (10.0),
    FATOR_18_30 (1.25),
    FATOR_30_60 (1.0),
    FATOR_60_90 (1.5);

    public final double calculo;

    CalcSeguro(double calculo) {
        this.calculo = calculo;
    }
    public double get() {
        return calculo;
    }

    public static CalcSeguro fatorIdade(int idade) {
        if (!Validacao.validaIdade(idade))
            throw new CadastroException("A idade deve ser maior que 18 anos e menor que 90 anos!");

        if (idade < 18)
            return null;
        if (idade < 30)
            return FATOR_18_30;
        if (idade < 60)
            return FATOR_30_60;
        if (idade < 90)
            return FATOR_60_90;
        return null;
    }
}