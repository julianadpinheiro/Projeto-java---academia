package mvc.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import mvc.model.Academia;
import mvc.model.AvaliacaoFisica;
import mvc.model.DivisaoTreino;
import mvc.model.DivisaoTreinoMusculo;
import mvc.model.EntradaAluno;
import mvc.model.Exercicio;
import mvc.model.ExercicioAplicacao;
import mvc.model.MensalidadeVigente;
import mvc.model.MovimentacaoFinanceira;
import mvc.model.PagamentoRecorrente;
import mvc.model.Pessoa;
import mvc.model.Treino;
import mvc.model.TreinoAplicacao;
import mvc.model.AlunoPagamentoMensalidade;
import mvc.model.Util;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juliana
 */
public class Gui {

    Util util = new Util();

    public Pessoa criarPessoa() {
        Pessoa pessoa = new Pessoa();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        pessoa.setNome(JOptionPane.showInputDialog("Digite o seu nome: "));
        pessoa.setSexo(JOptionPane.showInputDialog("Sexo: "));
        pessoa.setNascimento(LocalDate.parse(JOptionPane.showInputDialog("Data de nascimento: "), formatter));
        pessoa.setLogin(JOptionPane.showInputDialog("Digite seu login: "));
        pessoa.setSenha(JOptionPane.showInputDialog("Digite sua senha: "));
        pessoa.setTipoUsuario(JOptionPane.showInputDialog("Pessoa comum, Professor ou Administrador? "));
        JOptionPane.showMessageDialog(null, "Olá " + " " + pessoa.getNome()
                + "\n" + "Sexo:  " + pessoa.getSexo()
                + "\n" + " " + util.getDataFormatada(pessoa.getNascimento())
                + "\n" + "ID: " + pessoa.getId()
                + "\n" + " " + util.getDataCriacao()
                + "\n" + " " + util.getDataModificacao());
        return pessoa;
    }

    public Academia criarAcademia() {
        Academia academia = new Academia();
        academia.setNome(JOptionPane.showInputDialog("Digite o nome da academia: "));
        academia.setEndereco(JOptionPane.showInputDialog("Digite o endereço da academia: "));
        JOptionPane.showMessageDialog(null, "Nome: " + academia.getNome()
                + "\n" + "Endereco: " + academia.getEndereco()
                + "\n" + "ID: " + academia.getId()
                + "\n" + " " + util.getDataCriacao()
                + "\n" + " " + util.getDataModificacao());
        return academia;
    }

    public Exercicio criarExercicio() {
        Exercicio exercicio = new Exercicio();
        exercicio.setNome(JOptionPane.showInputDialog("Digite o nome do exercício: "));
        exercicio.setDescricao(JOptionPane.showInputDialog("Descreva o exercício: "));
        JOptionPane.showMessageDialog(null, " " + exercicio.getNome()
                + "\n" + "Descricao: " + exercicio.getDescricao()
                + "\n" + "ID: " + exercicio.getId()
                + "\n" + " " + util.getDataCriacao()
                + "\n" + " " + util.getDataModificacao());

        return exercicio;

    }

    public ExercicioAplicacao criarExercicioApl() {
        ExercicioAplicacao exercicioAp = new ExercicioAplicacao();
        exercicioAp.setDescricao(JOptionPane.showInputDialog("Digite a descrição do exercicio aplicacao: "));
        JOptionPane.showMessageDialog(null, "Descricao: " + exercicioAp.getDescricao()
                + "\n" + " " + util.getDataCriacao()
                + "\n" + " " + util.getDataModificacao());

        return exercicioAp;
    }

    public DivisaoTreino criarDivisaoTreino() {
        DivisaoTreino divisaoTreino = new DivisaoTreino();
        divisaoTreino.setNome(JOptionPane.showInputDialog("Digite o nome da divisao de treino: "));
        divisaoTreino.setDescricao(JOptionPane.showInputDialog("Descreva a divisao de treino: "));
        JOptionPane.showMessageDialog(null, "ID: " + divisaoTreino.getId()
                + "\n " + "Nome: " + divisaoTreino.getNome()
                + "\n" + "Descricao: " + divisaoTreino.getDescricao()
                + "\n" + " " + util.getDataCriacao()
                + "\n" + " " + util.getDataModificacao());

        return divisaoTreino;
    }

    public DivisaoTreinoMusculo criarDivisaoTreinoMusculo(DivisaoTreino divisaoTreino) {
        DivisaoTreinoMusculo divisaoTreinoMusculo = new DivisaoTreinoMusculo();

        divisaoTreinoMusculo.setDivisaoTreino(divisaoTreino);
        divisaoTreinoMusculo.setDescricao(JOptionPane.showInputDialog("Digite a descrição da divisao de treino musculo: "));
        JOptionPane.showMessageDialog(null, "Descricao: " + divisaoTreinoMusculo.getDescricao()
                + "\n" + " " + util.getDataCriacao()
                + "\n" + " " + util.getDataModificacao());

        return divisaoTreinoMusculo;

    }

    public Treino criarTreino(DivisaoTreino divisaoTreino) {
        Treino treino = new Treino();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        treino.setDivisaoTreino(divisaoTreino);

        treino.setObjetivo(JOptionPane.showInputDialog("Digite seu objetivo: "));
        treino.setDataInicio(LocalDate.parse(JOptionPane.showInputDialog("Digite a data de Inicio do treino: "), formatter));
        treino.setDataTermino(LocalDate.parse(JOptionPane.showInputDialog("Digite a data de termino do treino: "), formatter));

        JOptionPane.showMessageDialog(null, " " + divisaoTreino.toString()
                + "= " + divisaoTreino.getDescricao()
                + "\n" + "Objetivo: " + treino.getObjetivo()
                + "\n" + "Data Inicio: " + Util.getDataFormatada(treino.getDataInicio())
                + "\n" + "Data Termino: " + Util.getDataFormatada(treino.getDataTermino())
                + " - " + treino.retornaPeriodo() + " semanas"
                + "\n" + " " + Util.getDataCriacao()
                + "\n" + " " + Util.getDataModificacao());

        return treino;
    }

    public TreinoAplicacao criarTreinoAplicacao(DivisaoTreino divisaoTreino, DivisaoTreinoMusculo divisaoTreinoMusculo, Pessoa pessoa, Academia academia, Exercicio exercicio, ExercicioAplicacao exercicioAplicacao, Treino treino) {
        TreinoAplicacao treinoAplicacao = new TreinoAplicacao();

        treinoAplicacao.setPessoa(pessoa);

        treinoAplicacao.setDivisaoTreino(divisaoTreino);
        treinoAplicacao.setDivisaoTreinoMusculo(divisaoTreinoMusculo);
        treinoAplicacao.setExercicio(exercicio);
        treinoAplicacao.setAcademia(academia);
        treinoAplicacao.setExercicioAplicacao(exercicioAplicacao);
        treinoAplicacao.setTreino(treino);

        JOptionPane.showMessageDialog(null, "Treino " + treino.getId()
                + ", " + exercicio.getNome()
                + ", " + exercicioAplicacao.getDescricao()
                + ", " + divisaoTreino.getNome()
                + " " + divisaoTreinoMusculo.getDescricao()
        );
        return treinoAplicacao;
    }

    public AvaliacaoFisica criarAvaliacaoFisica(Treino treino) {
        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
        avaliacaoFisica.setUltimoTreino(treino);
        avaliacaoFisica.setPeso(Double.parseDouble(JOptionPane.showInputDialog("Digite seu peso:")));
        avaliacaoFisica.setAltura(Double.parseDouble(JOptionPane.showInputDialog("Digite sua altura:")));
        //calcula o imc
        avaliacaoFisica.setImc();
        avaliacaoFisica.setIndiceSatisfacao(Double.parseDouble(JOptionPane.showInputDialog("Digite o índice de satisfação (0 - 10):")));

        //mostra o imc calculado na tela
        String msg = String.format(" %.2f", avaliacaoFisica.getImc());
        JOptionPane.showMessageDialog(null, "Seu IMC é: " + msg
                + "\n" + "Data ultimo treino: " + treino.getDataInicio() + " / " + " " + treino.getDataTermino()
                + "\n" + " " + util.getDataCriacao()
                + "\n" + " " + util.getDataModificacao());

        return avaliacaoFisica;

    }

    public MensalidadeVigente criarMensalidade() {

        MensalidadeVigente mensalidadeVigente = new MensalidadeVigente();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        mensalidadeVigente.setValor(Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da mensalidade:")));
        mensalidadeVigente.setInicio(LocalDate.parse(JOptionPane.showInputDialog("Digite o inicio da mensalidade:"), formatter));
        mensalidadeVigente.setTermino(LocalDate.parse(JOptionPane.showInputDialog("Digite o fim da mensalidade:"), formatter));

        JOptionPane.showMessageDialog(null, "Valor mensalidade: " + mensalidadeVigente.getValor()
                + "\n" + "Inicio: " + util.getDataFormatada(mensalidadeVigente.getInicio())
                + "\n" + "Termino: " + util.getDataFormatada(mensalidadeVigente.getTermino())
                + "\n" + " " + util.getDataCriacao()
                + "\n" + " " + util.getDataModificacao());

        return mensalidadeVigente;
    }

    public AlunoPagamentoMensalidade criarAluno_pgt(MensalidadeVigente mensalidadeVigente, Pessoa pessoa) {
        AlunoPagamentoMensalidade aluno_pgt = new AlunoPagamentoMensalidade();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        aluno_pgt.setPessoa(pessoa);
        aluno_pgt.setMensalidade_vigente(mensalidadeVigente);
        aluno_pgt.setData_vencimento(LocalDate.parse(JOptionPane.showInputDialog("Digite a data do vencimento:"),formatter));
        aluno_pgt.setData_pagamento(LocalDate.parse(JOptionPane.showInputDialog("Digite a data do pagamento:"),formatter));
        aluno_pgt.setValor_pago(Double.parseDouble(JOptionPane.showInputDialog("Digite o valor pago:")));
        aluno_pgt.setModalidade(JOptionPane.showInputDialog("Digite a modalidade:"));
        
        JOptionPane.showMessageDialog(null," "  + pessoa.toString()
                + "\n" + mensalidadeVigente.toString()
                + "\n" + aluno_pgt.getValor_pago()
                + "\n" +  Util.calendario(aluno_pgt)
                + "\n" + " " + util.getDataCriacao()
                + "\n" + " " + util.getDataModificacao());
        

        return aluno_pgt;
    }

    /*
    public PagamentoRecorrente criarPagamentoRecorrente() {

        PagamentoRecorrente pagamentoRecorrente = new PagamentoRecorrente();
        JOptionPane.showMessageDialog(null, "Id: " + pagamentoRecorrente.getId() + "!");
        pagamentoRecorrente.setCartaoCredito(Long.parseLong(JOptionPane.showInputDialog("Insira os dados do cartao:")));

        return pagamentoRecorrente;
    }
     */
    public EntradaAluno criarAluno(AlunoPagamentoMensalidade alunoPagamentoMensalidade) {

        EntradaAluno entradaAluno = new EntradaAluno();
        entradaAluno.setAlunoPagamentoMensalidade(alunoPagamentoMensalidade);

        JOptionPane.showInputDialog("Digite a data de entrada: ");
        JOptionPane.showMessageDialog(null, "Id: " + entradaAluno.getId() + "!"
                + "\n" + util.getDataCriacao()
                + "\n" + util.getDataModificacao());
 
        return entradaAluno;

    }

    public MovimentacaoFinanceira criarMovimentacao() {
        MovimentacaoFinanceira movimentacaoFinanceira = new MovimentacaoFinanceira();
        movimentacaoFinanceira.setValor(Double.parseDouble(JOptionPane.showInputDialog("Digite o valor pago:")));
        movimentacaoFinanceira.setTipo(JOptionPane.showInputDialog("Salário do funcionário, Aluguel, Energia ou Outro?"));

        return movimentacaoFinanceira;

    }
}

