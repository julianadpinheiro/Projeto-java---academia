/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author victoria.santos
 */
public class MensalidadeVigente {
    
     Util util = new Util();
    private int id;
    private static int serial;
    private double valor;
    private LocalDate inicio;
    private LocalDate termino;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public MensalidadeVigente(){
        MensalidadeVigente.serial++;
        this.id = serial;
    }
    public int getId() {
        return id;
    }

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
     * @return the inicio
     */
    public LocalDate getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the termino
     */
    public LocalDate getTermino() {
        return termino;
    }

    /**
     * @param termino the termino to set
     */
    public void setTermino(LocalDate termino) {
        this.termino = termino;
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
        int hash = 5;
        hash = 89 * hash + this.id;
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
        final MensalidadeVigente other = (MensalidadeVigente) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "MensalidadeVigente: " + "id= " + id + " valor: " + valor + " inicio: " + inicio + " termino: " + termino;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

   
    
    
}
