/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.LocalDateTime;

/**
 *
 * @author victoria.santos
 */
public class MovimentacaoFinanceiraDAO {
    private MovimentacaoFinanceira[] movimentacoesFinanceiras = new MovimentacaoFinanceira[5];
    
    Util util = new Util();
    
     public MovimentacaoFinanceiraDAO(){
         MovimentacaoFinanceira j1 = new MovimentacaoFinanceira();
        j1.setDescricao("salario funcionario ");
        j1.setTipo("saida");
        j1.setValor(1.200);
        
        MovimentacaoFinanceira j2 = new MovimentacaoFinanceira();
        j2.setDescricao("Mensalidade ");
        j2.setTipo("entrada");
        j2.setValor(120);
        
        
        MovimentacaoFinanceira j3 = new MovimentacaoFinanceira();
        j3.setDescricao("Energia");
        j3.setTipo("saida");
        j3.setValor(423.6);
        
        
        
        
        
        
        this.adicionar(j1);
        this.adicionar(j2);
        this.adicionar(j3);
        
    }
    
    public MovimentacaoFinanceira buscaMovimentacaoPorId(int id ) {
         for (MovimentacaoFinanceira j : movimentacoesFinanceiras) {
            if (j != null && j.getId() == id)
                return j;
            }
        
        return null;
    }
    
    public boolean adicionar(MovimentacaoFinanceira movimentacaoFinanceira) {
        for (int i = 0; i < movimentacoesFinanceiras.length; i++) {
            if (movimentacoesFinanceiras[i] == null) {
                movimentacoesFinanceiras[i] = movimentacaoFinanceira;
                return true;
            } 
        }
        return false;
    }
    
    public boolean excluir(int id) {
        for (int i = 0; i < movimentacoesFinanceiras.length; i++) {
            if (movimentacoesFinanceiras[i] != null && movimentacoesFinanceiras[i].getId() == id) {
                movimentacoesFinanceiras[i] = null;
                return true;
            }
        }
        return false;
    }
    
    public boolean alterar(int idA, Double novoValor, Util util) {
       for (int i = 0; i < movimentacoesFinanceiras.length; i++) {
            if (movimentacoesFinanceiras[i] != null && movimentacoesFinanceiras[i].getId() == idA) {
                movimentacoesFinanceiras[i].setValor(novoValor);
                util.setDataModificacao(LocalDateTime.now());
                return true;
            }
        } 
       return false;
    }
    
    @Override
    public String toString() {
        String response = "";
        for (int i = 0; i < movimentacoesFinanceiras.length; i++) {
            MovimentacaoFinanceira movimentacaoFinanceira = movimentacoesFinanceiras[i];
            if (movimentacaoFinanceira != null) {
                response += "\n" + "ID: " + movimentacaoFinanceira.getId() 
                         + " Tipo: " + movimentacaoFinanceira.getDescricao()
                        + " Valor: " + movimentacaoFinanceira.getValor() 
                        + "\n" + " " + util.getDataCriacao() 
                        + "\n" + " " + util.getDataModificacao();
                      
            }
        }
        return response;
    }


   
}

