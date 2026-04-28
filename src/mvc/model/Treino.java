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
import java.time.temporal.ChronoUnit;

/**
 *
 * @author victoria.santos
 */
public class Treino {

    private int id;
    private static int serial;
    private String objetivo;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private Exercicio exercicio;
    private DivisaoTreino divisaoTreino;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

   
    public Treino(){
        Treino.serial++;
        this.id = serial;
    }
   
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * @param objetivo the objetivo to set
     */
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
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
    

    public LocalDate getDataInicio() {
         return dataInicio;
    }

    
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataTermino
     */
    public LocalDate getDataTermino() {
        return dataTermino;
    }

    /**
     * @param dataTermino the dataTermino to set
     */
    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    /**
     * @return the exercicio
     */
    public Exercicio getExercicio() {
        return exercicio;
    }

    /**
     * @param exercicio the exercicio to set
     */
    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    /**
     * @return the divisaoTreino
     */
    public DivisaoTreino getDivisaoTreino() {
        return divisaoTreino;
    }

    /**
     * @param divisaoTreino the divisaoTreino to set
     */
    public void setDivisaoTreino(DivisaoTreino divisaoTreino) {
        this.divisaoTreino = divisaoTreino;
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
    
     public long retornaPeriodo(){
        LocalDate agora = dataInicio;
        LocalDate outraData = dataTermino;

         long dias = ChronoUnit.DAYS.between(agora, outraData);
         long semanas = dias/7;
            return semanas;
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
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
        final Treino other = (Treino) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return  "ID: " + id + "\n" +
                "0bjetivo: " +"\n "+ objetivo 
                + " Inicio= " + dataInicio + "\n "+
                " Termino= " + dataTermino+ "\n" + 
                Util.getDataCriacao() + "\n" + Util.getDataModificacao();
    }

   
    
    

}
