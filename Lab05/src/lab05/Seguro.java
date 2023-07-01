package lab05;

import lab05.utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Seguro {
    protected final int id;
    protected LocalDate dataInicio;
    protected LocalDate dataFim;
    protected Seguradora seguradora;
    protected final ArrayList<Sinistro> listaSinitros;
    protected final ArrayList<Condutor> listaCondutores;
    protected double valorMensal;

    public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
        this.id = Utils.generateRandomId();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinitros = new ArrayList<>();
        this.listaCondutores = new ArrayList<>();

        calcularValor();
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public ArrayList<Sinistro> getListaSinitros() {
        return listaSinitros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public ArrayList<Sinistro> getSinistros() {
        return listaSinitros;
    }

    public boolean autorizarCondutor(Condutor condutor) {
        if (listaCondutores.contains(condutor)) {
            return false;
        }

        listaCondutores.add(condutor);

        calcularValor();
        return true;
    }

    public boolean desautorizarCondutor(Condutor condutor) {
        if (!listaCondutores.contains(condutor)) {
            return false;
        }

        listaCondutores.remove(condutor);

        calcularValor();
        return true;
    }

    public Sinistro gerarSinistro(LocalDate data, String endereco, Condutor condutor, Seguro seguro) {
        if (!listaCondutores.contains(condutor)) {
            return null;
        }

        return condutor.adicionarSinistro(data, endereco, seguro);
    }

    public abstract Cliente getCliente();

    protected abstract double calcularValor();

    protected void atualizarValor() {
        this.valorMensal = calcularValor();
    }

    @Override
    public String toString() {
        return "Seguro{" +
                "id=" + id +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", valorMensal=" + valorMensal +
                '}';
    }
}
