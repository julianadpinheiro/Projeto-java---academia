/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author juliana
 */
public class Util {

    private static LocalDateTime dataModificacao = LocalDateTime.now();
    private static LocalDateTime dataCriacao = LocalDateTime.now();

    public static String getDataCriacao() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataCriacao.format(formatador);
    }

    public static void setDataCriacao(LocalDateTime dataCriacao) {
        dataCriacao = dataCriacao;
    }

    public static String getDataModificacao() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataModificacao.format(formatador);
    }

    public static String getDataFormatada(LocalDate data) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return data.format(dtf);
    }
   
    

    public static void setDataModificacao(LocalDateTime dataModificacao) {
        dataModificacao = dataModificacao;
    }

    private static Pessoa pessoaLogada = null;

    private static LocalDateTime diaAtual = LocalDateTime.of(2023, Month.MARCH, 01, 22, 05);

    public static Pessoa getPessoaLogada() {
        if (pessoaLogada != null) {
            return pessoaLogada;
        }
        return null;
    }

    public static void setPessoaLogada(Pessoa PessoaLogado) {

        Util.pessoaLogada = PessoaLogado;
    }
    

    public static LocalDateTime getDiaAtual() {
        return diaAtual;
    }
    
    
    public static AlunoPagamentoMensalidade calendario(AlunoPagamentoMensalidade aluno_pgt){
        Calendar hoje = Calendar.getInstance();
        
        
        int ano = hoje.get((Calendar.YEAR));
        int mes = hoje.get((Calendar.MONTH));
        int dia = hoje.get((Calendar.DAY_OF_MONTH));
        int hora = hoje.get((Calendar.HOUR_OF_DAY));
        int minutos = hoje.get((Calendar.MINUTE));
        int segundos = hoje.get((Calendar.SECOND));
        
         //String dataHoje = String.format("%d/%d/%d %02d:%02d:%02d", dia, mes, ano, hora, minutos, segundos);
        

        //String dataProximaVencimento = String.format("%d/%d/%d", proximaVencimento.getDayOfMonth(), proximaVencimento.getMonthValue(), proximaVencimento.getYear());

        JOptionPane.showMessageDialog(null, 
            "Data de hoje: " + Util.getDataFormatada(aluno_pgt.getData_pagamento()) + "\n" +
            "Próxima data de vencimento: "  + Util.getDataFormatada(aluno_pgt.getData_vencimento().plusMonths(1))
        );
        
        return aluno_pgt;
    }
    /*
    public LocalDate calcularProximaDataVencimento(AlunoPagamentoMensalidade aluno_pgt) {
        if (aluno_pgt.getData_pagamento()!= null) {
            return aluno_pgt.getData_pagamento().plusMonths(1);
        }
        return aluno_pgt.getData_vencimento().plusMonths(1);
    }
    
    */
    public static int getDiaDoMes() {
        return diaAtual.getDayOfMonth();
    }

    public static void incrementaDias(int dias) {
        diaAtual.plusDays(dias);
    }

    public static void incrementaMes(int numeroMeses) {
        diaAtual.plusMonths(numeroMeses);
    }
    
    

}
