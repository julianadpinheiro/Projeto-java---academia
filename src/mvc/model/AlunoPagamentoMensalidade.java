/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JFrame;

/**
 *
 * @author juliana
 */
public class AlunoPagamentoMensalidade {

    private int id;
    private static int serial;
    private MensalidadeVigente mensalidade_vigente;
    private LocalDate data_vencimento;
    private LocalDate data_pagamento;
    private double valor_pago;
    private Pessoa pessoa;
    private String modalidade;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public AlunoPagamentoMensalidade(){
        AlunoPagamentoMensalidade.serial++;
        this.id = serial;
        
    }
    public int getId() {
        return id;
    }

    /**
     * @return the mensalidade_vigente
     */
    public MensalidadeVigente getMensalidade_vigente() {
        return mensalidade_vigente;
    }

    /**
     * @param mensalidade_vigente the mensalidade_vigente to set
     */
    public void setMensalidade_vigente(MensalidadeVigente mensalidade_vigente) {
        this.mensalidade_vigente = mensalidade_vigente;
    }

    /**
     * @return the data_vencimento
     */
    public LocalDate getData_vencimento() {
        return data_vencimento;
    }

    /**
     * @param data_vencimento the data_vencimento to set
     */
    public void setData_vencimento(LocalDate data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    /**
     * @return the data_pagamento
     */
    public LocalDate getData_pagamento() {
        return data_pagamento;
    }

    /**
     * @param data_pagamento the data_pagamento to set
     */
    public void setData_pagamento(LocalDate data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    /**
     * @return the valor_pago
     */
    public double getValor_pago() {
        return valor_pago;
    }

    /**
     * @param valor_pago the valor_pago to set
     */
    public void setValor_pago(double valor_pago) {
        this.valor_pago = valor_pago;
    }

    /**
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * @return the modalidade
     */
    public String getModalidade() {
        return modalidade;
    }

    /**
     * @param modalidade the modalidade to set
     */
    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    /**
     * @return the dataCriacao
     */
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    /**
     * @param dataCriacao the dataCriacao to set
     */
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    /**
     * @return the dataModificacao
     */
    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    /**
     * @param dataModificacao the dataModificacao to set
     */
    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
    
  


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlunoPagamentoMensalidade other = (AlunoPagamentoMensalidade) obj;
        return this.id == other.id;
    }
     
    
  

    @Override
    public String toString() {
        return "AlunoPagamentoMensalidade:" + " id:" + id +"\n" + " data_vencimento: " + data_vencimento 
                + " data_pagamento: " + data_pagamento + " valor pago: " + valor_pago 
                + " modalidade: " + modalidade;
    }
    
}
