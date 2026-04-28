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
public class DivisaoTreino {

    private int id = 1;
    private String nome;
    private static int serial;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    

    public DivisaoTreino(){
        DivisaoTreino.serial++;
        this.id = serial;
    }
    
    public String getNome() {
        return nome;
    }
    
      /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getId() {
        return id;
    }

    /**
     * @return the descricao
     */
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
        hash = 47 * hash + this.id;
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
        final DivisaoTreino other = (DivisaoTreino) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Treino " + nome + "(id = " + id + ")";
    }

  


    
}
