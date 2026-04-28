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
public class PagamentoRecorrente {
    private int id; 
    private static int serial;
    private Pessoa pessoa;
    private LocalDateTime data;
    private long cartaoCredito;
    private double valor; 
    private LocalDateTime dataInicio;
    private int mesesAutorizados;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;  

    /**
     * @return the id
     */
    
    public PagamentoRecorrente(){
        PagamentoRecorrente.serial++;
        this.id = serial;
    }
       
    public int getId() {
        return id;
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
     * @return the data
     */
    public LocalDateTime getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(LocalDateTime data) {
        this.data = data;
    }

    /**
     * @return the cartaoCredito
     */
    public long getCartaoCredito() {
        return cartaoCredito;
    }

    /**
     * @param cartaoCredito the cartaoCredito to set
     */
    public void setCartaoCredito(long cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the dataInicio
     */
    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the mesesAutorizados
     */
    public int getMesesAutorizados() {
        return mesesAutorizados;
    }

    /**
     * @param mesesAutorizados the mesesAutorizados to set
     */
    public void setMesesAutorizados(int mesesAutorizados) {
        this.mesesAutorizados = mesesAutorizados;
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
}
