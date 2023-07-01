package lab05;

import java.time.LocalDate;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(ClientePF cliente, Veiculo veiculo, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

    @Override
    public Cliente getCliente() {
        return cliente;
    }

    @Override
    protected double calcularValor() {
        double valor = CalcSeguro.VALOR_BASE.get() * CalcSeguro.fatorIdade(cliente.getIdade()).get();

        int quantidadeSinistrosCondutores = 0;
        int quantidadeSinistrosCliente = 0;
        for (Sinistro sinistro : listaSinitros) {
            if (sinistro.getSeguro() == this)
                quantidadeSinistrosCliente++;

            for (Condutor condutor : listaCondutores) {
                if (sinistro.getCondutor() == condutor)
                    quantidadeSinistrosCondutores++;
            }
        }
        valor *= (2.0 + quantidadeSinistrosCliente/10.0) * (3.0 + quantidadeSinistrosCondutores/10.0);
        return valor;
    }

    @Override
    public String toString() {
        return "SeguroPF{" +
                "veiculo=" + veiculo +
                ", cliente=" + cliente.getCpf() +
                '}';
    }
}
