/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author CAUPT - ALUNOS
 */
public class EntradaAluno {
    private int id;
    private AlunoPagamentoMensalidade alunoPagamentoMensalidade;
    private static int serial;
    private LocalDate data;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    
    public EntradaAluno(){
        EntradaAluno.serial++;
        this.id = serial;
    }
    
    public AlunoPagamentoMensalidade getAlunoPagamentoMensalidade() {
        return alunoPagamentoMensalidade;
    }

    public void setAlunoPagamentoMensalidade(AlunoPagamentoMensalidade alunoPagamentoMensalidade) {
        this.alunoPagamentoMensalidade = alunoPagamentoMensalidade;
    }
    public int getId() {
        return id;
    }

    /**
     * @return the data
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(LocalDate data) {
        this.data = data;
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
        int hash = 7;
        hash = 23 * hash + this.id;
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
        final EntradaAluno other = (EntradaAluno) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "EntradaAluno{" + "id=" + id + ", alunoPagamentoMensalidade=" + alunoPagamentoMensalidade + ", data=" + data + ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao + '}';
    }
    
    
}
