import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Seguradora> seguradoras;
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        clientes = new ArrayList<>();
        seguradoras = new ArrayList<>();


        Seguradora seguradora = new Seguradora("Seguros OBI", "(85) 99999-9999", "obi@unicamp.br", "Av. Erico Verissimo 1212");
        Cliente cliente1 = new ClientePF("Arthur", "Rua Alguma Rua 1111", "171.574.950-23", "Masculino", LocalDate.parse("01/01/2020", FORMATTER), "Ensino Medio", LocalDate.parse("07/03/2004", FORMATTER), "Medio");
        Cliente cliente2 = new ClientePJ("Empresa", "Rua Sem Criativade 9999", "52.843.570/0001-47", LocalDate.parse("01/01/2020", FORMATTER), 5);

        Veiculo veiculo1 = new Veiculo("NQL1212", "Fiat", "Palio", 2015);
        Veiculo veiculo2 = new Veiculo("ABC1213", "Fiat", "Uno", 2018);

        cliente1.adicionarVeiculo(veiculo1);
        cliente2.adicionarVeiculo(veiculo2);
        seguradora.cadastrarCliente(cliente1);
        seguradora.cadastrarCliente(cliente2);
        seguradora.gerarSinistro("07/01/2023", "Rua Sem Opcao 14", cliente1, veiculo1);
        seguradora.gerarSinistro("07/01/2023", "Rua Sem Opcao 15", cliente2, veiculo2);

        // Menu

        clientes.add(cliente1);
        clientes.add(cliente2);
        seguradoras.add(seguradora);

        Menu menu = new Menu();
        menu.add("Adicionar cliente", () -> adicionarCliente());
        menu.add("Remover cliente", () -> removerCliente());
        menu.add("Listar clientes", () -> listarClientes());
        menu.add("Adicionar seguradora", () -> adicionarSeguradora());
        menu.add("Remover seguradora", () -> removerSeguradora());
        menu.add("Listar seguradoras", () -> listarSeguradoras());
        menu.add("Menu clientes", () -> menuClientes());
        menu.add("Menu seguradoras", () -> menuSeguradoras());

        menu.run();
    }

    private static void adicionarCliente() {
        int tipo = ReadUtil.readInt("Tipo (PF = 1, PJ = 2): ");

        if (tipo == 1) {
            String nome = ReadUtil.readString("Nome: ");
            String endereco = ReadUtil.readString("Endereço: ");
            String CPF;
            for(;;) {
                CPF = ReadUtil.readString("CPF: ");
                if (!Validacao.validarCPF(CPF))
                    System.out.println("CPF inválido!");
                else
                    break;
            }

            String genero = ReadUtil.readString("Gênero: ");

            LocalDate dataLicenca;
            for(;;) {
                dataLicenca = ReadUtil.readLocalDate("Data da licença: ");
                if (dataLicenca == null)
                    System.out.println("Data inválida!");
                else
                    break;
            }

            String educacao = ReadUtil.readString("Educação: ");
            LocalDate dataNascimento;
            for(;;) {
                dataNascimento = ReadUtil.readLocalDate("Data de nascimento: ");
                if (dataNascimento == null)
                    System.out.println("Data inválida!");
                else if (!Validacao.validaIdade(dataNascimento))
                    System.out.println("Cliente deve ser maior de 18 anos e menor de 90 anos.");
                else
                    break;
            }
            String classeEconomica = ReadUtil.readString("Classe econômica: ");

            ClientePF cliente = new ClientePF(nome, endereco, CPF, genero, dataLicenca,
                educacao, dataNascimento, classeEconomica);

            clientes.add(cliente);
        } else if (tipo == 2) {
            String nome = ReadUtil.readString("Nome: ");
            String endereco = ReadUtil.readString("Endereço: ");
            String CNPJ;
            for(;;) {
                CNPJ = ReadUtil.readString("CNPJ: ");
                if (!Validacao.validarCNPJ(CNPJ))
                    System.out.println("CNPJ inválido!");
                else
                    break;
            }

            LocalDate dataFundacao;
            for(;;) {
                dataFundacao = ReadUtil.readLocalDate("Data de fundação: ");
                if (dataFundacao == null)
                    System.out.println("Data inválida!");
                else
                    break;
            }

            int qtdeFuncionarios;
            for (;;) {
                qtdeFuncionarios = ReadUtil.readInt("Quantidade de funcionários: ");
                if (qtdeFuncionarios < 0)
                    System.out.println("Número de funcionários não pode ser negativo!");
                else
                    break;
            };

            ClientePJ cliente = new ClientePJ(nome, endereco, CNPJ, dataFundacao, qtdeFuncionarios);
            clientes.add(cliente);
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private static void removerCliente() {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados!");
            return;
        }

        Cliente cliente = selecionarCliente();
        if (cliente != null)
            clientes.remove(cliente);
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados!");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println("- " + cliente);
        }
        System.out.println();
    }

    private static void adicionarSeguradora() {
        String nome = ReadUtil.readString("Nome: ");
        String telefone = ReadUtil.readString("Telefone: ");
        String email = ReadUtil.readString("Email: ");
        String endereco = ReadUtil.readString("Endereço: ");

        Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
        seguradoras.add(seguradora);
    }

    private static void removerSeguradora() {
        if (seguradoras.isEmpty()) {
            System.out.println("Não há seguradoras cadastradas!");
            return;
        }

        Seguradora seguradora = selecionarSeguradora();
        if (seguradora != null)
            seguradoras.remove(seguradora);
    }

    private static void listarSeguradoras() {
        if (seguradoras.isEmpty()) {
            System.out.println("Não há seguradoras cadastradas!");
            return;
        }

        for (Seguradora seguradora : seguradoras) {
            System.out.println("- " + seguradora);
        }

        System.out.println("");
    }

    private static void menuClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados!");
            return;
        }
        Cliente cliente = selecionarCliente();
        if (cliente == null)
            return;

        Menu menu = new Menu();
        menu.add("Adicionar veículo", () -> adicionarVeiculo(cliente));
        menu.add("Listar veículos", () -> listarVeiculos(cliente));
        menu.run();
    }

    private static void adicionarVeiculo(Cliente cliente) {
        String placa = ReadUtil.readString("Placa: ");
        String marca = ReadUtil.readString("Marca: ");
        String modelo = ReadUtil.readString("Modelo: ");
        int anoFabricacao = ReadUtil.readInt("Ano de fabricação: ");

        Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
        cliente.adicionarVeiculo(veiculo);
    }

    private static void listarVeiculos(Cliente cliente) {
        cliente.listarVeiculos("- ");
    }

    private static void menuSeguradoras() {
        if (seguradoras.isEmpty()) {
            System.out.println("Não há seguradoras cadastradas!");
            return;
        }
        Seguradora seguradora = selecionarSeguradora();
        if (seguradora == null)
            return;

        Menu menu = new Menu();
        menu.add("Cadastrar cliente", () -> cadastrarCliente(seguradora));
        menu.add("Remover cliente", () -> removerCliente(seguradora));
        menu.add("Adicionar sinistro", () -> adicionarSinistro(seguradora));
        menu.add("Visualizar sinistros", () -> visualizarSinistro(seguradora));
        menu.add("Transferir seguros", () -> transferirSeguros(seguradora));
        menu.add("Calcular receita", () -> calcularReceita(seguradora));

        menu.run();
    }

    private static void cadastrarCliente(Seguradora seguradora) {
        Cliente cliente = selecionarCliente();
        if (cliente == null)
            return;

        seguradora.cadastrarCliente(cliente);
    }

    private static void removerCliente(Seguradora seguradora) {
        Cliente cliente = selecionarCliente(Arrays.asList(seguradora.copiaListaClientes()));
        if (cliente == null)
            return;

        seguradora.removerCliente(cliente);
    }

    private static void adicionarSinistro(Seguradora seguradora) {
        String data = ReadUtil.readString("Data: ");
        String endereco = ReadUtil.readString("Endereço: ");
        Cliente cliente = selecionarCliente(Arrays.asList(seguradora.copiaListaClientes()));
        if (cliente == null)
            return;
        Veiculo veiculo = selecionarVeiculo(cliente);
        if (veiculo == null)
            return;

        seguradora.gerarSinistro(data, endereco, cliente, veiculo);
    }

    private static void visualizarSinistro(Seguradora seguradora) {
        Cliente cliente = selecionarCliente(Arrays.asList(seguradora.copiaListaClientes()));
        if (cliente == null)
            return;

        seguradora.visualizarSinistro(cliente);
    }

    private static void transferirSeguros(Seguradora seguradora) {
        Cliente cliente1 = selecionarCliente();
        if (cliente1 == null)
            return;
        Cliente cliente2 = selecionarCliente();
        if (cliente2 == null)
            return;

        seguradora.transferirSeguro(cliente1, cliente2);
    }
    private static void calcularReceita(Seguradora seguradora) {
        System.out.println(String.format("Receita: %.2f", seguradora.calcularReceita()));
    }

    // Funções para selecionar um objeto
    // Criam um menu com os objetos e retornam o objeto selecionado

    private static Cliente selecionarCliente(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados!");
            return null;
        }

        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ") " + clientes.get(i));
        }
        System.out.println(clientes.size() + 1 + ") Voltar");

        int opcao = ReadUtil.readInt("Escolha um cliente: ");
        if (opcao == clientes.size() + 1)
            return null;

        if (opcao < 1 || opcao > clientes.size()) {
            System.out.println("Cliente inválido!");
            return null;
        }

        return clientes.get(opcao - 1);
    }
    private static Cliente selecionarCliente() { return selecionarCliente(clientes); }

    private static Seguradora selecionarSeguradora(List<Seguradora> seguradoras) {
        if (seguradoras.isEmpty()) {
            System.out.println("Não há clientes cadastrados!");
            return null;
        }

        for (int i = 0; i < seguradoras.size(); i++) {
            System.out.println((i + 1) + ") " + seguradoras.get(i));
        }
        System.out.println(seguradoras.size() + 1 + ") Voltar");

        int opcao = ReadUtil.readInt("Escolha uma seguradora: ");
        if (opcao == seguradoras.size() + 1)
            return null;

        if (opcao < 1 || opcao > seguradoras.size()) {
            System.out.println("Cliente inválido!");
            return null;
        }

        return seguradoras.get(opcao - 1);
    }
    private static Seguradora selecionarSeguradora() { return selecionarSeguradora(seguradoras); }

    private static Veiculo selecionarVeiculo(Cliente cliente) {
        Veiculo[] veiculo = cliente.copiaListaVeiculos();

        if (veiculo.length == 0) {
            System.out.println("Não há veículos cadastrados nesse cliente!");
            return null;
        }

        for (int i = 0; i < veiculo.length; i++) {
            System.out.println((i + 1) + ") " + veiculo[i]);
        }
        System.out.println(veiculo.length + 1 + ") Voltar");

        int opcao = ReadUtil.readInt("Escolha um veículo: ");
        if (opcao == veiculo.length + 1)
            return null;

        if (opcao < 1 || opcao > veiculo.length) {
            System.out.println("Veículo inválido!");
            return null;
        }

        return veiculo[opcao - 1];
    }

}