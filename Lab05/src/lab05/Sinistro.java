package lab05;

import lab05.utils.DateParser;
import lab05.utils.Utils;

import java.time.LocalDate;
import java.util.UUID;

public class Sinistro {
    private final int id;
    private LocalDate data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;

    public Sinistro(LocalDate data, String endereco, Condutor condutor, Seguro seguro) {
        this.id = Utils.generateRandomId();
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
    }

    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }


    @Override
    public String toString() {
        return String.format("Sinistro{id=%d, data=%s, endereco='%s', condutor=%s, seguro=%s}",
                id, data.format(DateParser.FORMATTER), endereco, condutor, seguro);
    }
}
