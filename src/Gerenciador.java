import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal do sistema de gerenciamento de projetos.
 * Permite cadastrar usuários, projetos e equipes, listar informações e interagir via console.
 */
public class Gerenciador {

    // Listas que armazenam os objetos criados em memória
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Projeto> projetos = new ArrayList<>();
    private static final List<Equipe> equipes = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        // Loop do menu principal
        do {
            exibirMenu();
            opcao = Validador.pedirInteiro("Escolha uma opção: ", 0, 6);

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    cadastrarProjeto();
                    break;
                case 3:
                    cadastrarEquipe();
                    break;
                case 4:
                    listarUsuarios();
                    break;
                case 5:
                    listarProjetos();
                    break;
                case 6:
                    listarEquipes();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // Exibe o menu principal do sistema.
    
    private static void exibirMenu() {
        System.out.println("\n=== Sistema de Gerenciamento de Projetos ===");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Cadastrar Projeto");
        System.out.println("3. Cadastrar Equipe");
        System.out.println("4. Listar Usuários");
        System.out.println("5. Listar Projetos");
        System.out.println("6. Listar Equipes");
        System.out.println("0. Sair");
    }

    /**
     * Método para cadastrar um novo usuário no sistema.
     * Inclui validação de CPF único, email e perfil.
     */
    private static void cadastrarUsuario() {
        System.out.println("\n--- Cadastro de Usuário ---");

        String nome = Validador.pedirString("Nome completo: ");
        String cpf = Validador.pedirCpf("CPF: ");
        String email = Validador.pedirEmail("E-mail: ");
        String login = Validador.pedirString("Login: ");
        String senha = Validador.pedirString("Senha: ");
        int perfilOpcao = Validador.pedirInteiro("Perfil (1-ADMINISTRADOR, 2-GERENTE, 3-COLABORADOR): ", 1, 3);
        Usuario.Perfil perfil = Usuario.Perfil.values()[perfilOpcao - 1];

        // Verifica se já existe usuário com o mesmo CPF
        if (buscarUsuarioPorCpf(cpf) != null) {
            System.out.println("Já existe um usuário cadastrado com este CPF!");
            return;
        }

        // Cria e adiciona o usuário à lista
        usuarios.add(new Usuario(nome, cpf, email, login, senha, perfil));
        System.out.println("Usuário cadastrado com sucesso!");
    }

    /**
     * Método para cadastrar um novo projeto no sistema.
     * Permite definir gerente responsável, datas e status do projeto.
     */
    private static void cadastrarProjeto() {
        System.out.println("\n--- Cadastro de Projeto ---");

        String nome = Validador.pedirString("Nome do projeto: ");
        String descricao = Validador.pedirString("Descrição: ");
        LocalDate dataInicio = Validador.pedirData("Data de início (dd/MM/yyyy): ");
        LocalDate dataTermino = Validador.pedirData("Data de término prevista (dd/MM/yyyy): ");
        int statusOpcao = Validador.pedirInteiro("Status (1-PLANEJADO, 2-EM_ANDAMENTO, 3-CONCLUIDO, 4-CANCELADO): ", 1, 4);
        Projeto.Status status = Projeto.Status.values()[statusOpcao - 1];

        // Seleciona gerente responsável pelo projeto
        System.out.println("Selecione o gerente responsável:");
        listarUsuariosPorPerfil(Usuario.Perfil.GERENTE);
        String cpfGerente = Validador.pedirCpf("Digite o CPF do gerente: ");
        Usuario gerente = buscarUsuarioPorCpf(cpfGerente);

        if (gerente != null) {
            projetos.add(new Projeto(nome, descricao, dataInicio, dataTermino, status, gerente));
            System.out.println("Projeto cadastrado com sucesso!");
        } else {
            System.out.println("Gerente não encontrado.");
        }
    }

    /**
     * Método para cadastrar uma nova equipe.
     * Permite adicionar múltiplos membros.
     */
    private static void cadastrarEquipe() {
        System.out.println("\n--- Cadastro de Equipe ---");

        String nome = Validador.pedirString("Nome da equipe: ");
        String descricao = Validador.pedirString("Descrição: ");

        Equipe equipe = new Equipe(nome, descricao);
        String cpfMembro;

        // Loop para adicionar membros à equipe
        do {
            System.out.println("Adicionar membro (digite o CPF ou 'sair' para finalizar):");
            listarUsuarios();
            cpfMembro = Validador.pedirString("CPF ou 'sair': ");

            if (!cpfMembro.equalsIgnoreCase("sair")) {
                String cpfDigits = cpfMembro.replaceAll("\\D", "");
                if (!cpfDigits.matches("\\d{11}")) {
                    System.out.println("CPF inválido. Digite 11 números ou use 'sair'.");
                    continue;
                }

                Usuario membro = buscarUsuarioPorCpf(cpfDigits);
                if (membro != null) {
                    equipe.adicionarMembro(membro);
                    System.out.println("Membro adicionado!");
                } else {
                    System.out.println("Usuário não encontrado.");
                }
            }
        } while (!cpfMembro.equalsIgnoreCase("sair"));

        // Associa projetos à equipe
String opcaoProjeto;
do {
    if (projetos.isEmpty()) {
        System.out.println("Não há projetos cadastrados para vincular.");
        break;
    }

    System.out.println("Adicionar projeto à equipe (digite o nome ou 'sair' para finalizar):");
    listarProjetos();
    opcaoProjeto = Validador.pedirString("Projeto ou 'sair': ");

    if (!opcaoProjeto.equalsIgnoreCase("sair")) {
        Projeto projetoSelecionado = null;
        for (Projeto p : projetos) {
            if (p.getNome().equalsIgnoreCase(opcaoProjeto)) {
                projetoSelecionado = p;
                break;
            }
        }

        if (projetoSelecionado != null) {
            equipe.adicionarProjeto(projetoSelecionado);
            System.out.println("Projeto adicionado à equipe!");
        } else {
            System.out.println("Projeto não encontrado.");
        }
    }
        } while (!opcaoProjeto.equalsIgnoreCase("sair"));

        equipes.add(equipe);
        System.out.println("Equipe cadastrada com sucesso!");
    }

    // Lista todos os usuários cadastrados.
     
    private static void listarUsuarios() {
        System.out.println("\n--- Lista de Usuários ---");
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    // Lista todos os projetos cadastrados.
     
    private static void listarProjetos() {
        System.out.println("\n--- Lista de Projetos ---");
        for (Projeto p : projetos) {
            System.out.println(p);
        }
    }

   // Lista todas as equipes cadastradas e seus membros.
     
    private static void listarEquipes() {
        System.out.println("\n--- Lista de Equipes ---");
        for (Equipe e : equipes) {
            System.out.println(e);
            System.out.println("Membros:");
            e.listarMembros(true); // mostra detalhado
        }
    }

    // Lista usuários filtrando pelo perfil.
     
    private static void listarUsuariosPorPerfil(Usuario.Perfil perfil) {
        System.out.println("\n--- Usuários Disponíveis ---");
        for (Usuario u : usuarios) {
            if (u.getPerfil() == perfil) {
                System.out.println(u);
            }
        }
    }

    /**
     * Busca um usuário pelo CPF.
     * @param cpf CPF do usuário
     * @return Usuário encontrado ou null
     */
    private static Usuario buscarUsuarioPorCpf(String cpf) {
        for (Usuario u : usuarios) {
            if (u.getCpf().equals(cpf)) {
                return u;
            }
        }
        return null;
    }
}
