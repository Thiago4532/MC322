public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Thiago", "628.959.080-44", "7/3/2004", "Algum");
        Seguradora seguradora = new Seguradora("Seguro", "99999999", "seguro@unicamp.br", "Rua Erico Verissimo");
        Sinistro sinistro = new Sinistro("03/02/2001", "Rua Rua");
        Veiculo veiculo = new Veiculo("NQL-0202", "Fiat", "Palio");
    }
}
