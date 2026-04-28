/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.LocalDateTime;

/**
 *
 * @author juliana
 */
public class DivisaoTreinoMusculo {

    private int id;
    private static int serial;
    private String descricao;
    private DivisaoTreino divisaoTreino;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public DivisaoTreinoMusculo() {
        DivisaoTreinoMusculo.serial++;
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

    public DivisaoTreino getDivisaoTreino() {
        return divisaoTreino;
    }

    /**
     * @param divisaoTreino the divisaoTreino to set
     */
    public void setDivisaoTreino(DivisaoTreino divisaoTreino) {
        this.divisaoTreino = divisaoTreino;
    }

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
        hash = 73 * hash + this.id;
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
        final DivisaoTreinoMusculo other = (DivisaoTreinoMusculo) obj;
        return true;
    }

    @Override
    public String toString() {
        
        return descricao;

    }

   

}
