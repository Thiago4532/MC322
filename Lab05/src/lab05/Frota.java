package lab05;

import java.util.ArrayList;
import java.util.Arrays;

public class Frota {
    private String code;
    private final ArrayList<Veiculo> listaVeiculos;

    public Frota(String code) {
        this.code = code;
        this.listaVeiculos = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return listaVeiculos;
    }

    public boolean addVeiculo(Veiculo veiculo) {
        if (listaVeiculos.contains(veiculo)) {
            return false;
        }

        listaVeiculos.add(veiculo);
        return true;
    }

    public boolean removeVeiculo(Veiculo veiculo) {
        if (!listaVeiculos.contains(veiculo)) {
            return false;
        }

        listaVeiculos.remove(veiculo);
        return true;
    }

    @Override
    public String toString() {
        return "Frota " + code;
    }
}
