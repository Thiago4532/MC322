package lab05;

import lab05.utils.DateParser;

public class Main {
    public static void main(String[] args) {
        App app = App.getInstance();

        ClientePF arthur = new ClientePF("Arthur", "18233371041", "999999999", "Rua 1", "arthur@email.com", "Masculino", "nao sei", DateParser.parse("01/01/2000"));
        ClientePJ empresa = new ClientePJ("Empresa", "12345678901234", "999999999", "Rua 1", "empresa@email.com",
                DateParser.parse("01/01/2000"), 10);

        arthur.cadastrarVeiculo(new Veiculo("ABC1234", "Fiat", "Uno", 2010));
        Frota frota = new Frota("frota");
        frota.addVeiculo(new Veiculo("ABC1234", "Fiat", "Uno", 2010));

        app.run();
    }
}
