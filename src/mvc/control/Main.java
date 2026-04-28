package mvc.control;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
    import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import mvc.model.Academia;
import mvc.model.AcademiaDAO;
import mvc.model.AlunoPagamentoMensalidade;
import mvc.model.AlunoPagamentoMensalidadeDAO;
import mvc.model.AvaliacaoFisica;
import mvc.model.ConnectionFactory;
import mvc.model.DivisaoTreino;
import mvc.model.DivisaoTreinoDAO;
import mvc.model.DivisaoTreinoMusculo;
import mvc.model.DivisaoTreinoMusculoDAO;
import mvc.model.Exercicio;
import mvc.model.ExercicioDAO;
import mvc.model.ExercicioAplicacaoDAO;
import mvc.model.ExercicioAplicacao;
import mvc.model.MensalidadeVigente;
import mvc.model.MensalidadeVigenteDAO;
import mvc.model.Pessoa;
import mvc.model.EntradaAluno;
import mvc.model.EntradaAlunoDAO;
import mvc.model.MovimentacaoFinanceira;
import mvc.model.MovimentacaoFinanceiraDAO;
import mvc.model.PessoaDAO;
import mvc.model.Treino;
import mvc.model.TreinoDAO;
import mvc.model.TreinoAplicacao;
import mvc.model.TreinoAplicacaoDAO;
import mvc.model.Util;
import mvc.view.Gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juliana
 */
public class Main {

    Scanner scanner = new Scanner(System.in);

    Util util = new Util();
    private Gui gui = new Gui();
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private AcademiaDAO academiaDAO = new AcademiaDAO();
    private ExercicioDAO exercicioDAO = new ExercicioDAO();
    private ExercicioAplicacaoDAO exercicioAplicacaoDAO = new ExercicioAplicacaoDAO();
    private DivisaoTreinoDAO divisaoTreinoDAO = new DivisaoTreinoDAO();
    private DivisaoTreinoMusculoDAO divisaoTreinoMusculoDAO = new DivisaoTreinoMusculoDAO();
    private TreinoDAO treinoDAO = new TreinoDAO();
    private TreinoAplicacaoDAO treinoAplicacaoDAO = new TreinoAplicacaoDAO();
    private MensalidadeVigenteDAO mensalidadeVigenteDAO = new MensalidadeVigenteDAO();
    private AlunoPagamentoMensalidadeDAO alunoPagamentoMensalidadeDAO = new AlunoPagamentoMensalidadeDAO();
    private EntradaAlunoDAO entradaAlunoDAO = new EntradaAlunoDAO();
    private MovimentacaoFinanceiraDAO movimentacaoFinanceiraDAO = new MovimentacaoFinanceiraDAO();

//entrada --------------------------------------------------------------------------------
    public Main() {

        int opcaoUsuario = 10;

        while (opcaoUsuario != 2) {
            opcaoUsuario = this.menuLogar();
            switch (opcaoUsuario) {
                case 0:
                    try (Connection connection = ConnectionFactory.getConnection()) {
     
                        List<Pessoa> pessoas = null;
                        pessoas = pessoaDAO.lista(null);

                        String login = JOptionPane.showInputDialog("Login: ");
                        String senha = JOptionPane.showInputDialog("Senha:");
                        Pessoa logado = pessoaDAO.buscaPessoaPorLogin(login, senha);
                        if (logado != null) {
                            Util.setPessoaLogada(logado);
                        for (Pessoa c : pessoas) {
                        JOptionPane.showMessageDialog(null, "\n Pessoa Logado:  \n Ola! = " + pessoas);
                        }
                        
                        loopPrograma();
                        }

                    } catch (SQLException e) {
                        throw new RuntimeException("Erro ao adicionar pessoa: " + e.getMessage());
                    }

                case 1:
                   try (Connection connection = ConnectionFactory.getConnection()) {

                        Pessoa novaPessoa = gui.criarPessoa();

                        pessoaDAO.adicionar(novaPessoa);

                        System.out.println("Pessoa adicionada!\n");

                    } catch (SQLException e) {
                        throw new RuntimeException("Erro ao adicionar pessoa: " + e.getMessage());
                    }

                case 2:
                    System.out.println("Sair do menu");

                    break;

                default:
                    System.out.println("Escolha uma opcao valida!");
                    break;
            }
        }
    }

    private int menuLogar() {

        String input = JOptionPane.showInputDialog("0 - Logar\n1 - Cadastrar \n2 - Sair do Programa");

        return Integer.parseInt(input);
    }
//fim da entrada --------------------------------------------------------------------

//Looping crud de pessoa--------------------------------------------------------------------------------------
    private int menu() {

        String input = JOptionPane.showInputDialog(null, """
        0 - Para inserir Pessoa
        1 - Buscar Pessoa
        2 - Alterar uma Pessoa
        3 - Excluir uma Pessoa
        4 - Pagamento mensalidade
        5 - Entrada Aluno
        6 - Para sair do menu Login

        Qual é a sua opção?""");

        return Integer.parseInt(input);
    }

    private void loopPrograma() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 6) {
            opcaoUsuario = this.menu();
            switch (opcaoUsuario) {
                case 0:
                    try (Connection connection = ConnectionFactory.getConnection()) {

                        Pessoa novaPessoa = gui.criarPessoa();

                        pessoaDAO.adicionar(novaPessoa);

                        System.out.println("Pessoa adicionada!\n");

                    } catch (SQLException e) {
                        throw new RuntimeException("Erro ao adicionar pessoa: " + e.getMessage());
                    }
                    break;
                case 1:
                    try (Connection connection = ConnectionFactory.getConnection()) {
                        String login = JOptionPane.showInputDialog(null, "Digite o login: ");
                        String senha = JOptionPane.showInputDialog(null, "Digite a senha: ");
                        pessoaDAO.buscaPessoaPorLogin(login, senha);

                        System.out.println("Pessoa encontrada!");

                    } catch (SQLException e) {
                        throw new RuntimeException("Pessoa nao encontrada!" + e.getMessage());
                    }
                    break;

                case 2:
                    try (Connection connection = ConnectionFactory.getConnection()) {
                        String nome = JOptionPane.showInputDialog(null, "Digite o nome que deseja alterar: ");
                        String novoNome = JOptionPane.showInputDialog(null, "Qual o novo nome?");

                     pessoaDAO.alterar(nome, novoNome);

                      JOptionPane.showMessageDialog(null, "Alterado com sucesso!\n " + Util.getDataModificacao());

                    } catch (SQLException e) {
                        throw new RuntimeException("Nao alterado!" + e.getMessage());
                    }
                    break;

                case 3:
                     try (Connection connection = ConnectionFactory.getConnection()) {
                        String nomeExclui = JOptionPane.showInputDialog(null, "Digite o nome que deseja excluir: ");

                        Boolean excluido = pessoaDAO.excluir(nomeExclui);
                        
                        System.out.println("Excluído com sucesso!");
                    } catch (SQLException e) {
                        throw new RuntimeException("Erro na exclusao!" + e.getMessage());
                    }
                    break;
                case 4:
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    Double pgt = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do pagamento da mensalidade "));
                    LocalDate data = LocalDate.parse(JOptionPane.showInputDialog("Digite a data do pagamento da mensalidade "),formatter);
                    alunoPagamentoMensalidadeDAO.alterar(pgt, util , data);

                    break;
                case 5:
                    int idEscolhido = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID da mensalidade desejado:\n" + alunoPagamentoMensalidadeDAO.toString()));

                    AlunoPagamentoMensalidade alunoPagamentoMensalidade = alunoPagamentoMensalidadeDAO.buscaMensalidadePorId(idEscolhido);
                    EntradaAluno entradaAluno = gui.criarAluno(alunoPagamentoMensalidade);

                    VerificaEntradaAluno();

                    break;
                case 6:
                    System.out.println("Sair menu Login");
                    break;
                default:
                    System.out.println("Escolha uma opcao valida!");
                    break;
            }

        }
    }
//fim looping crud de pessoa---------------------------------------------------------------------

    private int menuEntradaAluno() {

        String input = JOptionPane.showInputDialog(null, """
        
        0 - Buscar Entrada Aluno
        1 - Excluir uma Entrada Aluno
        2 - Academia                                                                                                                                                                                                        
        3 - Para sair do menu Entrada Aluno

        Qual é a sua opção?""");

        return Integer.parseInt(input);
    }

    private void VerificaEntradaAluno() {

        int opcaoUsuario = 10;

        while (opcaoUsuario != 3) {
            opcaoUsuario = this.menuEntradaAluno();
            switch (opcaoUsuario) {
                case 0:
                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da entrada: "));

                    EntradaAluno busca = entradaAlunoDAO.buscaExercicioPorId(id);
                    if (busca != null) {
                        System.out.println("Entrada aluno Encontrada!");
                    } else {
                        System.out.println("Entrada aluno nao encontrada!");
                    }
                    break;
                case 1:
                    int idE = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da entrada: "));

                    Boolean excluir = entradaAlunoDAO.excluir(idE);
                    if (excluir != null) {
                        System.out.println("Entrada aluno excluida!");
                    } else {
                        System.out.println("Entrada aluno nao excluida!");
                    }

                    break;
                case 2:
                    Double pago = entradaAlunoDAO.metodoVeMensalidade(alunoPagamentoMensalidadeDAO);

                    if (pago != 0) {
                        JOptionPane.showMessageDialog(null,academiaDAO.lista(null));
                        
                        loopPrograma2();
                    }
                    System.out.println("procurar alguem na recepção para registrar o pagamento.");
                    break;
                case 3:
                    System.out.println("Sair menu Entrada");
                    break;
                default:
                    System.out.println("Escolha uma opcao valida!");
                    break;

            }
        }
    }

//crud academia ----------------------------------------------------------------------------------
    private int menuAcad() {

        String input = JOptionPane.showInputDialog(null, """
        0 - Para inserir Academia
        1 - Buscar Academia
        2 - Alterar uma Academia
        3 - Excluir uma Academia
        4 - Imprimir Ficha de Treino
        5 - inserir Avaliacao Fisica
        6 - Inserir Exercicio                                                                                                                                                                                                          
        7 - Para sair do menu Academia

        Qual é a sua opção?""");

        return Integer.parseInt(input);
    }

    //cliente 
    private void loopPrograma2(){

        int opcaoUsuario = 10;

        while (opcaoUsuario != 7) {
            opcaoUsuario = this.menuAcad();
            switch (opcaoUsuario) {
                case 0:
                   try(Connection connection = ConnectionFactory.getConnection()){
                       Academia criaA = gui.criarAcademia();
                    
                       academiaDAO.adicionar(criaA);

                        System.out.println("Academia adicionado com sucesso!\n");
                   }
                   catch(SQLException e) {
                      throw new RuntimeException("Erro ao adicionar academia: " + e.getMessage());
                   }
                    break;
                case 1:
                     try(Connection connection = ConnectionFactory.getConnection()){
                       String nome = JOptionPane.showInputDialog(null, "Digite o nome da academia: ");
                        Academia busca = academiaDAO.buscaAcademiaPorNome(nome);
                    
                        System.out.println("Academia encontrada! " + busca);
                   }
                   catch(SQLException e) {
                      throw new RuntimeException("Academia nao encontrada! " + e.getMessage());
                   }
                    break;
                case 2:
                    try(Connection connection = ConnectionFactory.getConnection()){
                        String nomeAntigo = JOptionPane.showInputDialog(null, "Digite o nome que deseja alterar: ");
                    String novoNome = JOptionPane.showInputDialog(null, "Qual o novo nome da academia?");

                    Boolean altera = academiaDAO.alterar(nomeAntigo, novoNome);
                    
                     JOptionPane.showMessageDialog(null, "Alterado com sucesso! \n " + Util.getDataModificacao());
                   }
                   catch(SQLException e) {
                      throw new RuntimeException("Academia nao encontrada! " + e.getMessage());
                   }
                    break;
                case 3:
                    try(Connection connection = ConnectionFactory.getConnection()){
                     String nomeExclui = JOptionPane.showInputDialog(null, "Digite o nome da academia que deseja excluir: ");

                    Boolean excluido = academiaDAO.excluir(nomeExclui);
                        System.out.println("Excluído com sucesso! ");
                    }
                    catch(SQLException e) {
                      throw new RuntimeException("Erro na exclusao! " + e.getMessage());
                    }
                    break;
                case 4:
                    //ARUMMAR O CASE para mostrar os treinos aplicaçao
                    JOptionPane.showMessageDialog(null,treinoAplicacaoDAO.lista(null));
                    break;
                case 5:
                    // DEPOIS DE CRIAR O TREINO VOLTAR AQUI
                    int idEscolhido = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID desejado:\n" + treinoDAO.lista(null)));

                    Treino treino = treinoDAO.buscaTreinoPorId(idEscolhido);

                    AvaliacaoFisica avaliacao = gui.criarAvaliacaoFisica(treino);

                    if (avaliacao != null) {

                        System.out.println("AvaliacaoFisica adicionada com sucesso!");
                    } else {
                        System.out.println(" Avaliacao Fisica nao adicionada!");
                    }

                    break;
                case 6:
                    loopVerificaUsuario();
                    break;
                case 7:
                    System.out.println("Sair menu Academia");
                    break;
                default:
                    System.out.println("Escolha uma opcao valida!");
                    break;

            }
        }
    }
//fim crud academia --------------------------------------------------------------------------------

    private void loopVerificaUsuario() {

        String tipoUsuario = Util.getPessoaLogada().getTipoUsuario();
        switch (tipoUsuario) {
            case "comum":
                System.out.println("Acesso permitido somente para professor e administrador! "); 
                break;

            case "professor":
                 


                loopPrograma3();
                break;

            case "administrador":
                loopPrograma3();
                break;

            default:
                JOptionPane.showMessageDialog(null, "Tipo de usuário desconhecido!");
        }
    }

//loopFichadeTreino();
//menu crud exercicio --------------------------------------------------------------------------------
    private int menuEx() {

        String input = JOptionPane.showInputDialog(null, """
        0 - Para inserir Exercício
        1 - Buscar Exercício
        2 - Alterar um Exercício
        3 - Excluir um Exercício
        4 - Mostrar exercicios inseridos
        5 - Inserir Exercício Aplicacao                                                                                                                                                                                                        
        6 - Para sair menu Exercício

        Qual é a sua opção?""");

        return Integer.parseInt(input);
    }

    private void loopPrograma3() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 6) {
            opcaoUsuario = this.menuEx();
            switch (opcaoUsuario) {
                case 0:
                     try(Connection connection = ConnectionFactory.getConnection()){
                       Exercicio criaEx = gui.criarExercicio();
                    
                       exercicioDAO.adicionar(criaEx);

                        System.out.println("Exercicio adicionado com sucesso!\n");
                   }
                   catch(SQLException e) {
                      throw new RuntimeException("Erro ao adicionar exercicio: " + e.getMessage());
                   }

                    break;
                case 1:
                      try(Connection connection = ConnectionFactory.getConnection()){
                       
                        String nome = JOptionPane.showInputDialog(null, "Digite o nome do exercicio");
                         exercicioDAO.buscaExercicioPorNome(nome);

                        System.out.println("Exercicio encontrado!");
                   }
                   catch(SQLException e) {
                      throw new RuntimeException("Exercicio nao encontrado!" + e.getMessage());
                   }
                    
                    break;

                case 2:
                    try(Connection connection = ConnectionFactory.getConnection()){
                       String nomeAntigo = JOptionPane.showInputDialog(null, "Qual exercício deseja alterar?");
                    String novoNome = JOptionPane.showInputDialog(null, "Qual o novo nome do exercício?");

                    exercicioDAO.alterar(nomeAntigo, novoNome);

                         JOptionPane.showMessageDialog(null, "Alterado com sucesso!\n " + util.getDataModificacao());
                    
                   }
                   catch(SQLException e) {
                      throw new RuntimeException("Exercicio nao alterado!" + e.getMessage());
                   }
                    break;
                case 3:
                    try(Connection connection = ConnectionFactory.getConnection()){
                       String nomeExclui = JOptionPane.showInputDialog(null, "Digite o nome do exercício que deseja excluir: ");

                            exercicioDAO.excluir(nomeExclui);
                  
                        System.out.println("Excluído com sucesso!");
                   }
                   catch(SQLException e) {
                      throw new RuntimeException("Erro na exclusao! " + e.getMessage());
                   }
                    break;
                case 4:
                    
                    List<Exercicio> exercicios = null;
                        exercicios = exercicioDAO.lista(null);
                        
                      for ( Exercicio c : exercicios) {
                        JOptionPane.showMessageDialog(null,c);
                        };

                    break;
                case 5:
                   List<ExercicioAplicacao> exerciciosaplicacao = null;
                        exerciciosaplicacao = exercicioAplicacaoDAO.lista(null);
                        
                      for ( ExercicioAplicacao c : exerciciosaplicacao) {
                        JOptionPane.showMessageDialog(null,c);
                        };

                    loopPrograma4();
                    break;

                case 6:
                    System.out.println("Sair menu Exercicio");
                    break;
                default:
                    System.out.println("Escolha uma opcao valida!");
                    break;

            }
        }

    }
//fim crud exercicio -------------------------------------------------------------------------------  

//menu crud exercicio aplicacao --------------------------------------------------------------------------------
    private int menuExApl() {

        String input = JOptionPane.showInputDialog(null, """
        0 - Para inserir Exercicio Aplicacao
        1 - Buscar Exercicio Aplicacao
        2 - Alterar um Exercicio Aplicacao
        3 - Excluir um Exercicio Aplicacao
        4 - Mostrar Exercicios Aplicacao                                                 
        5 - Divisao de Treino                                                                                                                                                                                                        
        6 - Para sair menu Exercicio Aplicacao

        Qual é a sua opção?""");

        return Integer.parseInt(input);
    }

    private void loopPrograma4() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 6) {
            opcaoUsuario = this.menuExApl();
            switch (opcaoUsuario) {
                case 0:
                     try(Connection connection = ConnectionFactory.getConnection()){
                      ExercicioAplicacao criaExApl = gui.criarExercicioApl();
                    
                       exercicioAplicacaoDAO.adicionar(criaExApl);

                        System.out.println("Exercicio aplicacao adicionado com sucesso!");
                   }
                   catch(SQLException e) {
                      throw new RuntimeException("Erro ao adicionar Exercicio Aplicacao: " + e.getMessage());
                   }
                    break;

                case 1:
                    try(Connection connection = ConnectionFactory.getConnection()){
                       int id = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do exercicio aplicacao: ")));
                        ExercicioAplicacao busca = exercicioAplicacaoDAO.buscaExercicioAplicacaoPorId(id);
                    
                        System.out.println("Exercicio aplicacao encontrado!");
                   }
                   catch(SQLException e) {
                      throw new RuntimeException("Exercicio aplicaçao nao encontrado!" + e.getMessage());
                   }
                    break;

                case 2:
                    try(Connection connection = ConnectionFactory.getConnection()){
                        int idA = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id de exercicio aplicacao: ")));
                    String novaDescricao = JOptionPane.showInputDialog(null, "Qual a nova descricao do exercício aplicacao? ");

                    Boolean altera = exercicioAplicacaoDAO.alterar(idA, novaDescricao);
                    
                     JOptionPane.showMessageDialog(null, "Alterado com sucesso! \n " + Util.getDataModificacao());
                   }
                   catch(SQLException e) {
                      throw new RuntimeException("Descricao nao alterada!" + e.getMessage());
                   }
                    

                    break;

                case 3:
                    try(Connection connection = ConnectionFactory.getConnection()){
                    int idB = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do exercicio aplicacao que deseja excluir: ")));

                    Boolean excluido = exercicioAplicacaoDAO.excluir(idB);
                        System.out.println("Excluído com sucesso! ");
                    }
                    catch(SQLException e) {
                      throw new RuntimeException("Erro na exclusao! " + e.getMessage());
                    }
                    
                    break;
                case 4:
                    List<ExercicioAplicacao> exerciciosaplicacao = null;
                        exerciciosaplicacao = exercicioAplicacaoDAO.lista(null);
                        
                      for ( ExercicioAplicacao c : exerciciosaplicacao) {
                        JOptionPane.showMessageDialog(null,c);
                        };
                    break;

                case 5:
                    List<DivisaoTreino> divisaoTreinos = null;
                    
                    JOptionPane.showMessageDialog(null,divisaoTreinoDAO.lista(null));
                      
                    loopPrograma5();
                    break;

                case 6:
                    System.out.println("Sair menu Exercicio Aplicacao");
                    break;

                default:
                    System.out.println("Escolha uma opcao vválida!");
                    break;

            }
        }
    }

//fim crud exercicio aplicacao -------------------------------------------------------------------------------  
//menu crud divisao de treino--------------------------------------------------------------------------------
    private int menuDivTreino() {

        String input = JOptionPane.showInputDialog(null, """
        0 - Para inserir Divisao de Treino
        1 - Buscar Divisao de Treino
        2 - Alterar uma descricao Divisao de Treino
        3 - Alterar o nome da Divisao de Treino                                                  
        4 - Excluir uma Divisao de Treino
        5 - Mostrar Divisao de Treino
        6 - Inserir Divisao de Treino-Musculo                                                                                                                                                                                                        
        7 - Para sair menu Divisao de Treino

        Qual é a sua opção?""");

        return Integer.parseInt(input);
    }

    private void loopPrograma5() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 7) {
            opcaoUsuario = this.menuDivTreino();
            switch (opcaoUsuario) {
                case 0:
                    try(Connection connection = ConnectionFactory.getConnection()){
                      DivisaoTreino criaDivisao = gui.criarDivisaoTreino();
                       divisaoTreinoDAO.adicionaERetornaId(criaDivisao);

                        System.out.println("Divisao de treino adicionada com sucesso!");
                   }
                   catch(SQLException e) {
                      throw new RuntimeException("Divisao de treino nao adicionada!" + e.getMessage());
                   }
                    break;

                case 1:
                    try(Connection connection = ConnectionFactory.getConnection()){
                      int id = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da divisao de treino: ")));
                    DivisaoTreino busca = divisaoTreinoDAO.buscaDivisaoPorId(id);
                        System.out.println("Divisao de treino encontrado!");
                   }
                   catch(SQLException e) {
                      throw new RuntimeException("Divisao de treino nao encontrado!"+ e.getMessage());
                   }
                case 2:
                    try(Connection connection = ConnectionFactory.getConnection()){
                     int idA = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da divisao de treino: ")));
                    String novaDescricao = JOptionPane.showInputDialog(null, "Deseja qual a nova descricao?");

                    Boolean altera = divisaoTreinoDAO.alterar(idA, novaDescricao);
                        JOptionPane.showMessageDialog(null, "Alterado com sucesso!\n " + Util.getDataModificacao());
             
                    }catch(SQLException e) {
                      throw new RuntimeException("Nao alterado!"+ e.getMessage());
                   }
                    break;

                case 3:
                    try(Connection connection = ConnectionFactory.getConnection()){
                     int id_alt = Integer.parseInt(JOptionPane.showInputDialog(null, "Qual id da divisao treino que deseja alterar?"));
                    String novoNome = JOptionPane.showInputDialog(null, "Deseja qual novo nome?");

                    Boolean alteraNome = divisaoTreinoDAO.alterarNome(id_alt, novoNome);
                        JOptionPane.showMessageDialog(null, "Alterado com sucesso!\n " + Util.getDataModificacao());
             
                    }catch(SQLException e) {
                      throw new RuntimeException("Nao alterado!"+ e.getMessage());
                   }
                    break;

                case 4:
                    try(Connection connection = ConnectionFactory.getConnection()){
                    int idB = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da divisao de treino que deseja excluir: ")));

                    divisaoTreinoDAO.excluir(idB);
                    
                    JOptionPane.showMessageDialog(null, "Excluido com sucesso!\n " + Util.getDataModificacao());
             
                    }catch(SQLException e) {
                      throw new RuntimeException("Erro na exclusao"+ e.getMessage());
                   }
                    break;
                   
                case 5:
                    List<DivisaoTreino> divisaoTreinos= null;
                       JOptionPane.showMessageDialog(null,divisaoTreinoDAO.lista(null));
                    break;
                case 6:
                     try{
                        int idEscolhido = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID desejado:\n" + divisaoTreinoDAO.lista(null)));
                        String nome = divisaoTreinoDAO.retornaNome(idEscolhido);
                        
                        DivisaoTreino divisaoTreino = divisaoTreinoDAO.buscaDivisaoPorId(idEscolhido);
                            
                        int numLetras = nome.replaceAll("\\s+", "").length();
                           
                        while (numLetras > 0) {
                        DivisaoTreinoMusculo criaDtM = gui.criarDivisaoTreinoMusculo(divisaoTreino);

                         divisaoTreinoMusculoDAO.adicionaERetornaId(criaDtM); 

                            System.out.println("Divisao de treino musculo adicionada com sucesso!");
        
                             numLetras--;
                        }
                           
                    } catch (NumberFormatException e) {
                       
                            throw new RuntimeException("Erro na conversão do ID: " + e.getMessage());
        
                    }
                    LoopPrograma6();
                    break;

                case 7:

                    System.out.println("Sair menu Divisao Treino");
                    break;

                default:
                    System.out.println("Escolha uma opcao válida!");
                    break;
            }

        }
    }

//fim crud exercicio divisao treino -------------------------------------------------------------------------------  
//menu crud divisao de treino-musculo--------------------------------------------------------------------------------
    private int menuDivTreinoMusc() {

        String input = JOptionPane.showInputDialog(null, """
        0 - Para inserir Divisao de Treino Musculo 
        1 - Buscar Divisao de Treino Musculo
        2 - Alterar uma Descricao Divisao de Treino Musculo                                          
        3 - Excluir uma Divisao de Treino Musculo
        4 - Mostrar Divisao de Treinos Musculo
        5 - Inserir um Treino (periodo que executara um treino)                                                                                                                                                                                                     
        6 - Para sair menu Divisao de Treino Musculo

        Qual é a sua opção?""");

        return Integer.parseInt(input);
    }

    private void LoopPrograma6() {
        int opcaoUsuario = 10;
        while (opcaoUsuario != 6) {
            opcaoUsuario = this.menuDivTreinoMusc();
            switch (opcaoUsuario) {
                case 0:
                    try{
                        int idEscolhido = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID desejado:\n" + divisaoTreinoDAO.lista(null)));
                        String nome = divisaoTreinoDAO.retornaNome(idEscolhido);
                        
                        DivisaoTreino divisaoTreino = divisaoTreinoDAO.buscaDivisaoPorId(idEscolhido);
                            
                        int numLetras = nome.replaceAll("\\s+", "").length();
                           
                        while (numLetras > 0) {
                        DivisaoTreinoMusculo criaDtM = gui.criarDivisaoTreinoMusculo(divisaoTreino);

                         divisaoTreinoMusculoDAO.adicionaERetornaId(criaDtM); 

                            System.out.println("Divisao de treino musculo adicionada com sucesso!");
        
                             numLetras--;
                        }
                    } catch (NumberFormatException e) {
                       
                            throw new RuntimeException("Erro na conversão do ID: " + e.getMessage());
        
                    }
                    break;
                case 1:
                    try(Connection connection = ConnectionFactory.getConnection()){
                    int id = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da divisao de treino musculo")));
                    DivisaoTreinoMusculo busca = divisaoTreinoMusculoDAO.buscaDivisaoPorId(id);
                       System.out.println("Divisao de treino musculo encontrado!");
             
                    }catch(SQLException e) {
                      throw new RuntimeException("Divisao de treino musculo nao encontrado!"+ e.getMessage());
                   }
                    break;
                case 2:
                    try(Connection connection = ConnectionFactory.getConnection()){
                    int idA = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da divisao de treino musculo")));
                    String novaDescricao = JOptionPane.showInputDialog(null, "Deseja qual a nova descricao?");

                    divisaoTreinoMusculoDAO.alterar(idA, novaDescricao);
                     JOptionPane.showMessageDialog(null, "Alterado com sucesso\n " + Util.getDataModificacao());
             
                    }catch(SQLException e) {
                      throw new RuntimeException("Nao alterado!"+ e.getMessage());
                   }
                    break;
                case 3:
                    try(Connection connection = ConnectionFactory.getConnection()){
                   int idB = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da divisao de treino musculo que deseja excluir: ")));

                    divisaoTreinoMusculoDAO.excluir(idB);
                   
                     System.out.println("Excluido com sucesso! ");
             
                    }catch(SQLException e) {
                      throw new RuntimeException("Erro na exclusao! "+ e.getMessage());
                   }
                    break;
                case 4:

                    JOptionPane.showMessageDialog(null, divisaoTreinoMusculoDAO.lista(null));
                    break;
                case 5:
                     try(Connection connection = ConnectionFactory.getConnection()){
                   int idEscolhido1 = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID desejado:\n" + divisaoTreinoDAO.lista(null)));

                    DivisaoTreino divisaoTreino1 = divisaoTreinoDAO.buscaDivisaoPorId(idEscolhido1);
                     Treino criaTreino = gui.criarTreino(divisaoTreino1);
                     treinoDAO.adicionaERetornaId(criaTreino); 
                     System.out.println("Treino adicionado com sucesso!");
                     
                    }catch(SQLException e) {
                      throw new RuntimeException("Treino nao adicionado!"+ e.getMessage());
                   }
                    LoopPrograma7();
                    break;
                case 6:
                    System.out.println("Sair menu divisao treino musculo");
                    break;
                default:
                    System.out.println("Escolha uma opcao valida!");
                    break;

            }
        }
    }
//fim crud exercicio divisao treino-musculo -------------------------------------------------------------------------------  
//menu crud treino---------------------------------------------------------------------------------------------------------

    private int menuTreino() {

        String input = JOptionPane.showInputDialog(null, """
        0 - Para inserir Treino 
        1 - Buscar Treino
        2 - Alterar um Treino                                           
        3 - Excluir um Treino
        4 - Inserir um Treino Aplicacao                                                                                                                                                                                                     
        5-  Para sair do menu Treino 

        Qual é a sua opção?""");

        return Integer.parseInt(input);
    }

    private void LoopPrograma7() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 5) {
            opcaoUsuario = this.menuTreino();
            switch (opcaoUsuario) {
                case 0:
                     try(Connection connection = ConnectionFactory.getConnection()){
                   int idEscolhido1 = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID desejado:\n" + divisaoTreinoDAO.lista(null)));

                    DivisaoTreino divisaoTreino1 = divisaoTreinoDAO.buscaDivisaoPorId(idEscolhido1);
                     Treino criaTreino = gui.criarTreino(divisaoTreino1);
                     treinoDAO.adicionaERetornaId(criaTreino); 
                     System.out.println("Treino adicionado com sucesso!");
                     
                    }catch(SQLException e) {
                      throw new RuntimeException("Treino nao adicionado!"+ e.getMessage());
                   }
                    break;

                case 1:
                     try(Connection connection = ConnectionFactory.getConnection()){
                    int id = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id treino: ")));
                    Treino busca = treinoDAO.buscaTreinoPorId(id);
                     
                   
                     System.out.println("Treino encontrado!");
                     
                    }catch(SQLException e) {
                      throw new RuntimeException("Treino nao encontrado!"+ e.getMessage());
                   }
                case 2:
                    try(Connection connection = ConnectionFactory.getConnection()){
                    int idA = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do treino: ")));
                    String novoObjetivo = JOptionPane.showInputDialog(null, "Deseja qual o novo objetivo?");
                       treinoDAO.alterar(idA, novoObjetivo);
                     
                   
                     JOptionPane.showMessageDialog(null, "Alterado com sucesso!\n " + util.getDataModificacao());
                     
                    }catch(SQLException e) {
                      throw new RuntimeException("Nao alterado!"+ e.getMessage());
                   }
                    break;

                case 3:
                    try(Connection connection = ConnectionFactory.getConnection()){
                    int idB = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do treino que deseja excluir: ")));

                    Boolean excluido = treinoDAO.excluir(idB);
                   
                     System.out.println("Excluído com sucesso!");
                    }catch(SQLException e) {
                      throw new RuntimeException("Erro na exclusao! "+ e.getMessage());
                   }
                   break;
                  
                case 4:
                    try{
                     int idP = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID da pessoa:\n" + pessoaDAO.lista(null)));
                    Pessoa pessoa = pessoaDAO.buscaPessoaPorId(idP);

                    int idAC = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID da academia:\n" + academiaDAO.lista(null)));
                    Academia academia = academiaDAO.buscaAcademiaPorId(idAC);

                    int idE = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID do exercício:\n" + exercicioDAO.lista(null)));
                    Exercicio exercicio = exercicioDAO.buscaExercicioPorId(idE);

                    int idEA = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID do exercício aplicação:\n" + exercicioAplicacaoDAO.lista(null)));
                    ExercicioAplicacao exercicioAplicacao = exercicioAplicacaoDAO.buscaExercicioAplicacaoPorId(idEA);

                    int idT = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID da divisão de treino:\n" + divisaoTreinoDAO.lista(null)));
                    DivisaoTreino divisaoTreinoB = divisaoTreinoDAO.buscaDivisaoPorId(idT);

                    int idTM = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID da divisão de treino músculo:\n" + divisaoTreinoMusculoDAO.lista(null)));
                    DivisaoTreinoMusculo divisaoTreinoMusculo = divisaoTreinoMusculoDAO.buscaDivisaoPorId(idTM);

                    int idTreino = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID desejado do treino:\n" + treinoDAO.lista(null)));
                    Treino treino = treinoDAO.buscaTreinoPorId(idTreino);

                    TreinoAplicacao criaTreinoAplicacao = gui.criarTreinoAplicacao(divisaoTreinoB, divisaoTreinoMusculo, pessoa, academia, exercicio, exercicioAplicacao, treino);
                    
                    treinoAplicacaoDAO.adicionaERetornaId(criaTreinoAplicacao);
                   JOptionPane.showMessageDialog(null, "Treino aplicação adicionado com sucesso!\n");
                                
                
                    }catch(HeadlessException | NumberFormatException e) {
                      throw new RuntimeException("Treino aplicação não foi adicionado!" + e.getMessage());
                    }
                    LoopPrograma8();
                    break;
                    
                case 5:
                    System.out.println("Sair menu Treino");
                    break;

                default:
                    System.out.println("Escolha uma opcao valida!");
                    break;
            }
        }
    }
//fim crud exercicio treino --------------------------------------------------------------------------------------------------------------  

 private int menuTreinoAplicacao() {

        String input = JOptionPane.showInputDialog(null, """
        0- -Inserir treino Aplicacao                                                
        1 - Buscar Treino Aplicacao                                         
        2 - Excluir um Treino Aplicacao
        3 - Mostrar Treino Aplicacao
        4 - Inserir a Mensalidade Vigente                                                                                                                                                                                                                                         
        5 - Para sair do menu Treino 

        Qual é a sua opção?""");

        return Integer.parseInt(input);
    }

    private void LoopPrograma8() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 5) {
            opcaoUsuario = this.menuTreinoAplicacao();
            switch (opcaoUsuario) {
                case 0:
                    try{
                     int idP = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID da pessoa:\n" + pessoaDAO.lista(null)));
                    Pessoa pessoa = pessoaDAO.buscaPessoaPorId(idP);

                    int idAC = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID da academia:\n" + academiaDAO.lista(null)));
                    Academia academia = academiaDAO.buscaAcademiaPorId(idAC);

                    int idE = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID do exercício:\n" + exercicioDAO.lista(null)));
                    Exercicio exercicio = exercicioDAO.buscaExercicioPorId(idE);

                    int idEA = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID do exercício aplicação:\n" + exercicioAplicacaoDAO.lista(null)));
                    ExercicioAplicacao exercicioAplicacao = exercicioAplicacaoDAO.buscaExercicioAplicacaoPorId(idEA);

                    int idT = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID da divisão de treino:\n" + divisaoTreinoDAO.lista(null)));
                    DivisaoTreino divisaoTreinoB = divisaoTreinoDAO.buscaDivisaoPorId(idT);

                    int idTM = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID da divisão de treino músculo:\n" + divisaoTreinoMusculoDAO.lista(null)));
                    DivisaoTreinoMusculo divisaoTreinoMusculo = divisaoTreinoMusculoDAO.buscaDivisaoPorId(idTM);

                    int idTreino = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID desejado do treino:\n" + treinoDAO.lista(null)));
                    Treino treino = treinoDAO.buscaTreinoPorId(idTreino);

                    TreinoAplicacao criaTreinoAplicacao = gui.criarTreinoAplicacao(divisaoTreinoB, divisaoTreinoMusculo, pessoa,academia, exercicio, exercicioAplicacao, treino);
                    
                    treinoAplicacaoDAO.adicionaERetornaId(criaTreinoAplicacao);
                   JOptionPane.showMessageDialog(null, "Treino aplicação adicionado com sucesso!\n");
                                
                
                    }catch(HeadlessException | NumberFormatException e) {
                      throw new RuntimeException("Treino aplicação não foi adicionado!" + e.getMessage());
                    }
                    
                    
                case 1:
                    try(Connection connection = ConnectionFactory.getConnection()){
                    int id = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do treino aplicacao")));
                    TreinoAplicacao busca = treinoAplicacaoDAO.buscaTreinoAplicacaoPorId(id);
                   
                     System.out.println("Treino Aplicacao encontrado!");
                    }catch(SQLException e) {
                      throw new RuntimeException("Treino Aplicacao nao encontrado!"+ e.getMessage());
                   }
                    break;

                case 2:
                    try(Connection connection = ConnectionFactory.getConnection()){
                    int idB = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do treino aplicação que deseja excluir: ")));

                    Boolean excluido = treinoAplicacaoDAO.excluir(idB);
                   
                     System.out.println("Excluído com sucesso! ");
                    }catch(SQLException e) {
                      throw new RuntimeException("Erro na exclusao! "+ e.getMessage());
                   }
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, treinoAplicacaoDAO.lista(null));
                    break;

                case 4:
                    try (Connection connection = ConnectionFactory.getConnection()) {
                    String tipoUsuario = Util.getPessoaLogada().getTipoUsuario();

                    if ("administrador".equals(tipoUsuario)) {
                    MensalidadeVigente criaMensalidade = gui.criarMensalidade();
                            LoopPrograma9(); // Chama o método LoopPrograma9 após criar a mensalidade
                        } else {
                        System.out.println("Acesso permitido somente para administradores");
                        }
                            } catch (SQLException e) {
                            throw new RuntimeException("Erro ao adicionar mensalidade: " + e.getMessage(), e);
                        }
                case 5:
                    System.out.println("Sair menu Treino Aplicacao");
                    break;

                default:
                    System.out.println("Escolha uma opcao valida!");
                    break;
            }
        }
    }

//fim crud exercicio treino aplicacao -------------------------------------------------------------------------------  
//menu crud  mensalidade---------------------------------------------------------------------------------------------------------
    private int menuMensalidade() {

        String input = JOptionPane.showInputDialog(null, """
        0 - Para inserir Mensalidade Vigente
        1 - Buscar Mensalidade Vigente
        2 - Alterar uma Mensalidade Vigente                                         
        3 - Excluir uma Mensalidade Vigente
        4 - Inserir Aluno Pagamento Mensalidade                                                                                                                                                                                           
        5 - Para sair do menu Mensalidade Vigente

        Qual é a sua opção?""");

        return Integer.parseInt(input);
    }

    private void LoopPrograma9() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 5) {
            opcaoUsuario = this.menuMensalidade();
            switch (opcaoUsuario) {
                case 0:
                    try(Connection connection = ConnectionFactory.getConnection()){
                    MensalidadeVigente criaMensalidade = gui.criarMensalidade();
                    
                    mensalidadeVigenteDAO.adicionar(criaMensalidade);
                   
                    }catch(SQLException e) {
                      throw new RuntimeException("Mensalidade nao adicionado"+ e.getMessage());
                   }
                    break;

                case 1:
                     try(Connection connection = ConnectionFactory.getConnection()){
                    int id = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da mensalidade vigente: ")));
                    MensalidadeVigente busca = mensalidadeVigenteDAO.buscaMensalidadePorId(id);
                    System.out.println("Mensalidade vigente encontrada!");
                   
                    }catch(SQLException e) {
                      throw new RuntimeException("Mensalidade vigente nao encontrada!"+ e.getMessage());
                   }
                    break;
                case 2:
                     try(Connection connection = ConnectionFactory.getConnection()){
                    int idA = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da mensalidade vigente:")));
                    Double novoValor = (Double.parseDouble(JOptionPane.showInputDialog(null, "Qual é o novo valor?")));

                    Boolean altera = mensalidadeVigenteDAO.alterar(idA, novoValor);
                   
                    }catch(SQLException e) {
                      throw new RuntimeException("Valor nao alterado!"+ e.getMessage());
                   }
                   
                    break;

                case 3:
                    try(Connection connection = ConnectionFactory.getConnection()){
                    int idB = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da mensalidade vigente que deseja excluir: ")));

                    Boolean excluido = mensalidadeVigenteDAO.excluir(idB);
                   
                    }catch(SQLException e) {
                      throw new RuntimeException("Erro na exclusao! "+ e.getMessage());
                   }
                   
                    

                    break;

                case 4:
                    int idEscolhido = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID desejado:\n" + mensalidadeVigenteDAO.toString()));
                    int idPessoa = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID da pessoa desejado:\n" + pessoaDAO.lista(null)));
                    MensalidadeVigente mensalidadeVigente = mensalidadeVigenteDAO.buscaMensalidadePorId(idEscolhido);
                    Pessoa pessoa = pessoaDAO.buscaPessoaPorId(idPessoa);

                    AlunoPagamentoMensalidade criaAluno = gui.criarAluno_pgt(mensalidadeVigente, pessoa);
                    if (alunoPagamentoMensalidadeDAO.adicionar(criaAluno)) {
                        System.out.println("Pagamento adicionado com sucesso!");
                    } else {
                        System.out.println("Pagamento nao adicionadoo!");
                    }
                    LoopPrograma10();
                    break;

                case 5:
                    System.out.println("Sair menu da Mensalidade Vigente");
                    break;

                default:
                    System.out.println("Escolha uma opcao valida!");
                    break;
            }
        }
    }

    private int menuPagamento() {

        String input = JOptionPane.showInputDialog(null, """
        0 - Para inserir Aluno Pagamento Mensalidade
        1 - Buscar Aluno Pagamento Mensalidade
        2 - Alterar Aluno Pagamento Mensalidade                                        
        3 - Excluir Aluno Pagamento Mensalidade
        4 - Inserir Movimentacao Financeira                                                                                                                                                                                     
        5 - Para sair do menu Aluno Pagamento Mensalidade

        Qual é a sua opção?""");

        return Integer.parseInt(input);
    }

    private void LoopPrograma10() {
        
        int opcaoUsuario = 10;

        while (opcaoUsuario != 5) {
            opcaoUsuario = this.menuPagamento();
            switch (opcaoUsuario) {
                case 0:
                    int idEscolhido = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID desejado:\n" + mensalidadeVigenteDAO.toString()));
                    int idPessoa = Integer.parseInt(JOptionPane.showInputDialog("Informe qual o ID da pessoa desejado:\n" + pessoaDAO.lista(null)));
                    MensalidadeVigente mensalidadeVigente = mensalidadeVigenteDAO.buscaMensalidadePorId(idEscolhido);
                    Pessoa pessoa = pessoaDAO.buscaPessoaPorId(idPessoa);

                    AlunoPagamentoMensalidade criaAluno = gui.criarAluno_pgt(mensalidadeVigente, pessoa);
                    if (alunoPagamentoMensalidadeDAO.adicionar(criaAluno)) {
                        System.out.println("Pagamento adicionado com sucesso!");
                    } else {
                        System.out.println("Pagamento nao adicionadoo!");
                    }
                    break;
                case 1:
                    int id = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do pagamento: ")));
                    AlunoPagamentoMensalidade busca = alunoPagamentoMensalidadeDAO.buscaMensalidadePorId(id);
                    if (busca != null) {
                        System.out.println("Pagamento encontrado!");
                    } else {
                        System.out.println("Pagamento nao encontrado!");
                    }
                    break;
                case 2:
                    int idA = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do pagamento:")));
                    Double novoValor = (Double.parseDouble(JOptionPane.showInputDialog(null, "Qual é o novo valor?")));

                    Boolean altera = alunoPagamentoMensalidadeDAO.alterar2(idA, novoValor, util);

                    if (altera) {
                        JOptionPane.showMessageDialog(null, "Valor alterado com sucesso!\n " + util.getDataModificacao());
                    } else {
                        JOptionPane.showMessageDialog(null, "Valor nao alterado!");
                    }
                    break;
                case 3:
                    int idB = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do pagamento que deseja excluir: ")));

                    Boolean excluido = alunoPagamentoMensalidadeDAO.excluir(idB);

                    if (excluido) {
                        System.out.println("Excluido com sucesso! ");
                    } else {
                        System.out.println("Erro na exclusao! ");
                    }
                    break;
                case 4:
                        JOptionPane.showMessageDialog(null, movimentacaoFinanceiraDAO.toString());
                    loopPrograma13();
                    break;
                case 5:
                    System.out.println("Sair menu ");
                    break;
                default:
                    System.out.println("Escolha uma opcao valida!");
                    break;

            }
        }
    }

    private int menuMovimentacaoFinanceira() {
        String input = JOptionPane.showInputDialog(null, """
        0 - Para inserir Movimentacao Financeira
        1 - Buscar Movimentacao Financeira
        2 - Alterar uma Movimentacao Financeira
        3 - Excluir uma Movimentacao Financeira
        4 - Mostrar Movimentacao Financeira                                                 
        5 - Para sair do menu Movimentacao Financeira

        Qual é a sua opção?""");

        return Integer.parseInt(input);
    }

    private void loopPrograma13() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 5) {
            opcaoUsuario = this.menuMovimentacaoFinanceira();
            switch (opcaoUsuario) {
                case 0:
                    
                    MovimentacaoFinanceira criaMovimentacao = gui.criarMovimentacao();
                    if (movimentacaoFinanceiraDAO.adicionar(criaMovimentacao)) {
                        System.out.println("Movimentação financeira adicionada com sucesso!");
                    } else {
                        System.out.println("Movimentação financeira não adicionada!");
                    }
                    break;

                case 1:
                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da movimentação financeira:"));
                    MovimentacaoFinanceira busca = movimentacaoFinanceiraDAO.buscaMovimentacaoPorId(id);
                    if (busca != null) {
                        System.out.println("Movimentação financeira encontrada!");
                    } else {
                        System.out.println("Movimentação financeira não encontrada!");
                    }
                    break;

                case 2:
                    int idA = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da Movimentação Financeira:"));
                    Double novoValor = Double.parseDouble(JOptionPane.showInputDialog(null, "Qual é o novo valor?"));

                    Boolean altera = movimentacaoFinanceiraDAO.alterar(idA, novoValor, util);

                    if (altera) {
                        JOptionPane.showMessageDialog(null, "Valor alterado com sucesso!\n " + util.getDataModificacao());
                    } else {
                        JOptionPane.showMessageDialog(null, "Valor não alterado!");
                    }
                    break;

                case 3:
                    int idB = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da Movimentação Financeira que deseja excluir:"));

                    Boolean excluido = movimentacaoFinanceiraDAO.excluir(idB);

                    if (excluido) {
                        System.out.println("Excluída com sucesso!");
                    } else {
                        System.out.println("Erro na exclusão!");
                    }
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null, movimentacaoFinanceiraDAO.toString());
                    break;

                case 5:
                    System.out.println("Sair do menu de Movimentação Financeira");
                    this.loopPrograma();
                    break;
                    
                default:
                    System.out.println("Escolha uma opção válida!");
                    break;
            }
        }
    }
    
    public static void main(String[] args) {
        new Main();
    }
}
