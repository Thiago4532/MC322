package lab05;

import java.time.LocalDate;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;

    public SeguroPJ(ClientePJ cliente, Frota frota, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
    }
    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }

    @Override
    public Cliente getCliente() {
        return cliente;
    }

    @Override
    protected double calcularValor() {
        int quantidadeFunc = cliente.getQtdeFuncionarios();
        int quantidadeVeiculos = frota.getVeiculos().size();
        int AnosPosFundacao = LocalDate.now().getYear() - cliente.getDataFundacao().getYear();
        int quantidadeSinistrosCliente = 0;
        int quantidadeSinistrosCondutores = 0;
        for (Sinistro sinistro : listaSinitros) {
            if (sinistro.getSeguro() == this)
                quantidadeSinistrosCliente++;

            for (Condutor condutor : listaCondutores) {
                if (sinistro.getCondutor() == condutor)
                    quantidadeSinistrosCondutores++;
            }
        }


        return CalcSeguro.VALOR_BASE.get() * (10.0 + (quantidadeFunc)/10.0) *
                (1 + 1.0/(quantidadeVeiculos+2)) *
                (1 + 1.0/(AnosPosFundacao+2)) *
                (2.0 + quantidadeSinistrosCliente/10.0) *
                (3.0 + quantidadeSinistrosCondutores/10.0);
    }

    @Override
    public String toString() {
        return "SeguroPJ{" +
                "frota=" + frota +
                ", cliente=" + cliente +
                ", id=" + id +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", valorMensal=" + valorMensal +
                '}';
    }
}
