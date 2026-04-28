/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author juliana
 */
public class AlunoPagamentoMensalidadeDAO {
   
    private AlunoPagamentoMensalidade[] alunoPagamentoMensalidades  = new AlunoPagamentoMensalidade [5];
    
    public AlunoPagamentoMensalidadeDAO (){
         AlunoPagamentoMensalidade j1 = new AlunoPagamentoMensalidade();
         j1.setValor_pago(0); 
       
        this.adicionar(j1);
        
    }

    public AlunoPagamentoMensalidade[] getAlunoPagamentoMensalidades() {
        return alunoPagamentoMensalidades;
    }
    
   
    
    public AlunoPagamentoMensalidade buscaMensalidadePorId(int id ) {
         for (AlunoPagamentoMensalidade j : alunoPagamentoMensalidades ) {
            if (j != null && j.getId()== id)
                return j;
            }
        
        return null;
    }
    
    
    public boolean adicionar(AlunoPagamentoMensalidade alunoPagamentoMensalidade) {
        for (int i = 0; i < alunoPagamentoMensalidades.length; i++) {
            if (alunoPagamentoMensalidades[i] == null) {
              alunoPagamentoMensalidades[i] = alunoPagamentoMensalidade;
                return true;
            } 
        }
        return false;
    }
    
    public boolean excluir(int id) {
        for (int i = 0; i < alunoPagamentoMensalidades.length; i++) {
            if (alunoPagamentoMensalidades[i] != null && alunoPagamentoMensalidades[i].getId()== id) {
                alunoPagamentoMensalidades[i] = null;
                return true;
            }
            
        }
        return false;
    }
    public boolean alterar(Double Valor,Util util,LocalDate data) {
       for (int i = 0; i < alunoPagamentoMensalidades.length; i++) {
            if (alunoPagamentoMensalidades[i] != null) {
                alunoPagamentoMensalidades[i].setValor_pago(Valor);
                alunoPagamentoMensalidades[i].setData_pagamento(data);
                util.setDataModificacao(LocalDateTime.now());
                return true;
            }
            
        } 
       return false;
    }
     public boolean alterar2(int id,Double Valor,Util util) {
       for (int i = 0; i < alunoPagamentoMensalidades.length; i++) {
            if (alunoPagamentoMensalidades[i] != null) {
                alunoPagamentoMensalidades[i].setValor_pago(Valor);
                util.setDataModificacao(LocalDateTime.now());
                return true;
            }
            
        } 
       return false;
    }
     
    
    
    @Override
    public String toString() {
        String response = new String();
        for (int i = 0; i < alunoPagamentoMensalidades.length; i++) {
           AlunoPagamentoMensalidade alunoPagamentoMensalidade = alunoPagamentoMensalidades[i];
            if(alunoPagamentoMensalidade != null)
                response += "ID: " + alunoPagamentoMensalidade.getId() + " Valor: " + alunoPagamentoMensalidade.getValor_pago()+ "\n";
        }
        return response;
    }
    
}
