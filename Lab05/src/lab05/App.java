package lab05;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    private static App INSTANCE = null;
    private final ArrayList<Cliente> clientes;
    private final ArrayList<Seguradora> seguradoras;
    private final Menu mainMenu;

    public static App getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new App();
        }

        return INSTANCE;
    }

    private App() {
        clientes = new ArrayList<>();
        seguradoras = new ArrayList<>();
        mainMenu = new Menu();
    }

    public void run() {
        mainMenu.add("Adicionar cliente", () -> adicionarCliente());
        mainMenu.add("Remover cliente", () -> removerCliente());
        mainMenu.add("Listar clientes", () -> listarClientes());
        mainMenu.add("Adicionar seguradora", () -> adicionarSeguradora());
        mainMenu.add("Remover seguradora", () -> removerSeguradora());
        mainMenu.add("Listar seguradoras", () -> listarSeguradoras());
        mainMenu.add("Menu clientes", () -> menuClientes());
        mainMenu.add("Menu seguradoras", () -> menuSeguradoras());
    }

    // Funções do menu

    private void adicionarCliente() {
        int tipo = ReadUtil.readInt("Tipo (PF = 1, PJ = 2): ");

        if (tipo == 1) {
            String nome = ReadUtil.readString("Nome: ");
            String CPF;
            for (; ; ) {
                CPF = ReadUtil.readString("CPF: ");
                if (!Validacao.validarCPF(CPF))
                    System.out.println("CPF inválido!");
                else
                    break;
            }
            String telefone = ReadUtil.readString("Telefone: ");
            String endereco = ReadUtil.readString("Endereço: ");
            String email = ReadUtil.readString("E-mail: ");
            String genero = ReadUtil.readString("Gênero: ");

            String educacao = ReadUtil.readString("Educação: ");
            LocalDate dataNascimento;
            for (; ; ) {
                dataNascimento = ReadUtil.readLocalDate("Data de nascimento: ");
                if (dataNascimento == null)
                    System.out.println("Data inválida!");
                else if (!Validacao.validaIdade(dataNascimento))
                    System.out.println("Cliente deve ser maior de 18 anos e menor de 90 anos.");
                else
                    break;
            }

            ClientePF cliente = new ClientePF(nome, CPF, telefone, endereco, email, genero, educacao, dataNascimento);

            clientes.add(cliente);
        } else if (tipo == 2) {
            String nome = ReadUtil.readString("Nome: ");
            String CNPJ;
            for (; ; ) {
                CNPJ = ReadUtil.readString("CNPJ: ");
                if (!Validacao.validarCNPJ(CNPJ))
                    System.out.println("CNPJ inválido!");
                else
                    break;
            }
            String telefone = ReadUtil.readString("Telefone: ");
            String endereco = ReadUtil.readString("Endereço: ");
            String email = ReadUtil.readString("E-mail: ");

            LocalDate dataFundacao;
            for (; ; ) {
                dataFundacao = ReadUtil.readLocalDate("Data de fundação: ");
                if (dataFundacao == null)
                    System.out.println("Data inválida!");
                else
                    break;
            }

            int qtdeFuncionarios;
            for (; ; ) {
                qtdeFuncionarios = ReadUtil.readInt("Quantidade de funcionários: ");
                if (qtdeFuncionarios < 0)
                    System.out.println("Número de funcionários não pode ser negativo!");
                else
                    break;
            }

            ClientePJ cliente = new ClientePJ(nome, CNPJ, telefone, endereco, email, dataFundacao, qtdeFuncionarios);
            clientes.add(cliente);
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private void removerCliente() {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados!");
            return;
        }

        Cliente cliente = selecionarCliente();
        if (cliente != null)
            clientes.remove(cliente);
    }

    private void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados!");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println("- " + cliente);
        }
        System.out.println("");
    }

    private void adicionarSeguradora() {
        String nome = ReadUtil.readString("Nome: ");
        String CNPJ;
        for (; ; ) {
            CNPJ = ReadUtil.readString("CNPJ: ");
            if (!Validacao.validarCNPJ(CNPJ))
                System.out.println("CNPJ inválido!");
            else
                break;
        }
        String telefone = ReadUtil.readString("Telefone: ");
        String endereco = ReadUtil.readString("Endereço: ");
        String email = ReadUtil.readString("E-mail: ");

        Seguradora seguradora = new Seguradora(nome, CNPJ, telefone, endereco, email);
        seguradoras.add(seguradora);
    }

    private void removerSeguradora() {
        if (seguradoras.isEmpty()) {
            System.out.println("Não há seguradoras cadastradas!");
            return;
        }

        Seguradora seguradora = selecionarSeguradora();
        if (seguradora != null)
            seguradoras.remove(seguradora);
    }

    private void listarSeguradoras() {
        if (seguradoras.isEmpty()) {
            System.out.println("Não há seguradoras cadastradas!");
            return;
        }

        for (Seguradora seguradora : seguradoras) {
            System.out.println("- " + seguradora);
        }

        System.out.println("");
    }

    private void menuClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados!");
            return;
        }
        Cliente cliente = selecionarCliente();
        if (cliente == null)
            return;

        if (cliente instanceof ClientePF)
            menuClientePF((ClientePF) cliente);
        else if (cliente instanceof ClientePJ)
            menuClientePJ((ClientePJ) cliente);
    }

    private void menuClientePF(ClientePF cliente) {
        Menu menu = new Menu();
        menu.add("Cadastrar veículo", () -> cadastrarVeiculo(cliente));
        menu.add("Listar veículos", () -> listarVeiculos(cliente));
        menu.run();
    }

    private Veiculo novoVeiculo() {
        String placa = ReadUtil.readString("Placa: ");
        String marca = ReadUtil.readString("Marca: ");
        String modelo = ReadUtil.readString("Modelo: ");
        int anoFabricacao = ReadUtil.readInt("Ano de fabricação: ");

        Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
        return veiculo;
    }

    private void cadastrarVeiculo(ClientePF cliente) {
        cliente.cadastrarVeiculo(novoVeiculo());
    }

    private void listarVeiculos(ClientePF cliente) {
        for (Veiculo veiculo : cliente.getVeiculos()) {
            System.out.println("- " + veiculo);
        }
    }

    private void menuClientePJ(ClientePJ cliente) {
        Menu menu = new Menu();
        menu.add("Cadastrar frota", () -> cadastrarFrota(cliente));
        menu.add("Listar frotas", () -> listarFrotas(cliente));
        menu.add("Adicionar veículo a frota", () -> adicionarVeiculoFrota(cliente));
        menu.add("Listar veículos da frota", () -> listarVeiculosFrota(cliente));
        menu.run();
    }

    private void cadastrarFrota(ClientePJ cliente) {
        String code = ReadUtil.readString("Código: ");
        cliente.cadastrarFrotas(new Frota(code));
    }

    private void listarFrotas(ClientePJ cliente) {
        for (Frota frota : cliente.getFrotas()) {
            System.out.println("- " + frota);
        }
    }

    private void adicionarVeiculoFrota(ClientePJ cliente) {
        if (cliente.getFrotas().isEmpty()) {
            System.out.println("Não há frotas cadastradas!");
            return;
        }

        Frota frota = selecionarFrota(cliente);
        if (frota == null)
            return;

        frota.addVeiculo(novoVeiculo());
    }

    private void listarVeiculosFrota(ClientePJ cliente) {
        if (cliente.getFrotas().isEmpty()) {
            System.out.println("Não há frotas cadastradas!");
            return;
        }

        Frota frota = selecionarFrota(cliente);
        if (frota == null)
            return;

        for (Veiculo veiculo : frota.getVeiculos()) {
            System.out.println("- " + veiculo);
        }
    }

    private void menuSeguradoras() {
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
        menu.add("Gerar seguro", () -> adicionarSeguro(seguradora));
        menu.add("Visualizar seguro", () -> visualizarSeguro(seguradora));

        // TODO
//        menu.add("Gerar sinistro", () -> gerarSinistro(seguradora));
//        menu.add("Calcular receita", () -> calcularReceita(seguradora));


        menu.run();
    }

    private void cadastrarCliente(Seguradora seguradora) {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados!");
            return;
        }

        Cliente cliente = selecionarCliente();
        if (cliente == null)
            return;

        seguradora.cadastrarCliente(cliente);
    }

    private void removerCliente(Seguradora seguradora) {
        if (seguradora.getListaClientes().isEmpty()) {
            System.out.println("Não há clientes cadastrados!");
            return;
        }

        Cliente cliente = selecionarCliente(seguradora.getListaClientes());
        if (cliente == null)
            return;

        seguradora.removerCliente(cliente);
    }

    private void adicionarSeguro(Seguradora seguradora) {
        if (seguradora.getListaClientes().isEmpty()) {
            System.out.println("Não há clientes cadastrados!");
            return;
        }

        Cliente cliente = selecionarCliente(seguradora.getListaClientes());
        if (cliente == null)
            return;

        if (cliente instanceof ClientePF)
            adicionarSeguroPF(seguradora, (ClientePF) cliente);
        else if (cliente instanceof ClientePJ)
            adicionarSeguroPJ(seguradora, (ClientePJ) cliente);
    }

    private void adicionarSeguroPF(Seguradora seguradora, ClientePF cliente) {
        if (cliente.getVeiculos().isEmpty()) {
            System.out.println("Não há veículos cadastrados!");
            return;
        }

        Veiculo veiculo = selecionarVeiculo(cliente);
        if (veiculo == null)
            return;

        LocalDate dataInicio;
        for (;;) {
            dataInicio = ReadUtil.readLocalDate("Data inicio: ");
            if (dataInicio == null)
                System.out.println("Data inválida!");
            else if (!Validacao.validaIdade(dataInicio))
                System.out.println("Cliente deve ser maior de 18 anos e menor de 90 anos.");
            else
                break;
        }
        LocalDate dataFim;
        for (;;) {
            dataFim = ReadUtil.readLocalDate("Data fim: ");
            if (dataFim == null)
                System.out.println("Data inválida!");
            else if (!Validacao.validaIdade(dataFim))
                System.out.println("Cliente deve ser maior de 18 anos e menor de 90 anos.");
            else
                break;
        }

        seguradora.gerarSeguro(cliente, veiculo, dataInicio, dataFim);
    }

    private void adicionarSeguroPJ(Seguradora seguradora, ClientePJ cliente) {
        if (cliente.getFrotas().isEmpty()) {
            System.out.println("Não há frotas cadastradas!");
            return;
        }

        Frota frota = selecionarFrota(cliente);
        if (frota == null)
            return;

        LocalDate dataInicio;
        for (;;) {
            dataInicio = ReadUtil.readLocalDate("Data inicio: ");
            if (dataInicio == null)
                System.out.println("Data inválida!");
            else if (!Validacao.validaIdade(dataInicio))
                System.out.println("Cliente deve ser maior de 18 anos e menor de 90 anos.");
            else
                break;
        }
        LocalDate dataFim;
        for (;;) {
            dataFim = ReadUtil.readLocalDate("Data fim: ");
            if (dataFim == null)
                System.out.println("Data inválida!");
            else if (!Validacao.validaIdade(dataFim))
                System.out.println("Cliente deve ser maior de 18 anos e menor de 90 anos.");
            else
                break;
        }

        seguradora.gerarSeguro(cliente, frota, dataInicio, dataFim);
    }

    private void visualizarSeguro(Seguradora seguradora) {
        if (seguradora.getListaClientes().isEmpty()) {
            System.out.println("Não há clientes cadastrados!");
            return;
        }

        for (Seguro seguro : seguradora.getListaSeguros()) {
            System.out.println("- " + seguro);
        }
    }

    private void gerarSinistro(Seguradora seguradora) {
        if (seguradora.getListaSeguros().isEmpty()) {
            System.out.println("Não há seguros cadastrados!");
            return;
        }

        Seguro seguro = selecionarSeguro(seguradora);
        if (seguro == null)
            return;

        LocalDate data;
        for (;;) {
            data = ReadUtil.readLocalDate("Data do sinistro: ");
            if (data == null)
                System.out.println("Data inválida!");
            else if (!Validacao.validaIdade(data))
                System.out.println("Cliente deve ser maior de 18 anos e menor de 90 anos.");
            else
                break;
        }
    }

    // Funções para selecionar um objeto
    // Criam um menu com os objetos e retornam o objeto selecionado

    private Cliente selecionarCliente(List<Cliente> clientes) {
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
    private Cliente selecionarCliente() { return selecionarCliente(clientes); }

    private Seguradora selecionarSeguradora(List<Seguradora> seguradoras) {
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
    private Seguradora selecionarSeguradora() { return selecionarSeguradora(seguradoras); }

    private Veiculo selecionarVeiculo(ClientePF cliente) {
        Veiculo[] veiculo = cliente.getVeiculos().toArray(new Veiculo[0]);

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

    private Frota selecionarFrota(ClientePJ cliente) {
        Frota[] frota = cliente.getFrotas().toArray(new Frota[0]);

        if (frota.length == 0) {
            System.out.println("Não há frotas cadastradas nesse cliente!");
            return null;
        }

        for (int i = 0; i < frota.length; i++) {
            System.out.println((i + 1) + ") " + frota[i]);
        }
        System.out.println(frota.length + 1 + ") Voltar");

        int opcao = ReadUtil.readInt("Escolha uma frota: ");
        if (opcao == frota.length + 1)
            return null;

        if (opcao < 1 || opcao > frota.length) {
            System.out.println("Frota inválida!");
            return null;
        }

        return frota[opcao - 1];
    }

    private Seguro selecionarSeguro(Seguradora seguradora) {
        Seguro[] seguro = seguradora.getListaSeguros().toArray(new Seguro[0]);

        if (seguro.length == 0) {
            System.out.println("Não há seguros cadastrados nessa seguradora!");
            return null;
        }

        for (int i = 0; i < seguro.length; i++) {
            System.out.println((i + 1) + ") " + seguro[i]);
        }
        System.out.println(seguro.length + 1 + ") Voltar");

        int opcao = ReadUtil.readInt("Escolha um seguro: ");
        if (opcao == seguro.length + 1)
            return null;

        if (opcao < 1 || opcao > seguro.length) {
            System.out.println("Seguro inválido!");
            return null;
        }

        return seguro[opcao - 1];
    }

    // Getters

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Seguradora> getSeguradoras() {
        return seguradoras;
    }
}
