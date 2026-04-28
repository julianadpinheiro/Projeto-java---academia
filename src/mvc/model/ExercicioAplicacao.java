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
public class ExercicioAplicacao {

    private int id=1;
    private static int serial;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public ExercicioAplicacao(){
        ExercicioAplicacao.serial++;
        this.id = serial;
    }

    public int getId() {
        return id;
    }
    
     /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        hash = 79 * hash + this.id;
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
        final ExercicioAplicacao other = (ExercicioAplicacao) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" 
                + "Descricao=" + descricao +
                 "\n" + " DataCriacao: " + "\n" 
                + Util.getDataCriacao() + "\n" + "DataModificacao: " + Util.getDataModificacao();
    }

   
    

}
