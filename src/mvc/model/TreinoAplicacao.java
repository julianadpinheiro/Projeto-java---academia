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
public class TreinoAplicacao {
  private int id;
    private static int serial;
    private Pessoa pessoa;
    private Academia academia;
    private DivisaoTreinoMusculo divisaoTreinoMusculo;
    private Treino treino;
    private Exercicio exercicio;
    private ExercicioAplicacao exercicioAplicacao;
    private DivisaoTreino divisaoTreino;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
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
     * @return the academia
     */
    public Academia getAcademia() {
        return academia;
    }

    /**
     * @param academia the academia to set
     */
    public void setAcademia(Academia academia) {
        this.academia = academia;
    }

    /**
     * @return the divisaoTreinoMusculo
     */
    public DivisaoTreinoMusculo getDivisaoTreinoMusculo() {
        return divisaoTreinoMusculo;
    }

    /**
     * @param divisaoTreinoMusculo the divisaoTreinoMusculo to set
     */
    public void setDivisaoTreinoMusculo(DivisaoTreinoMusculo divisaoTreinoMusculo) {
        this.divisaoTreinoMusculo = divisaoTreinoMusculo;
    }

  

   
    public TreinoAplicacao(){
        TreinoAplicacao.serial++;
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
   
    /**
     * @return the treino
     */
    public Treino getTreino() {
        return treino;
    }

    /**
     * @param treino the treino to set
     */
    public void setTreino(Treino treino) {
        this.treino = treino;
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
     * @return the exercicioAplicacao
     */
    public ExercicioAplicacao getExercicioAplicacao() {
        return exercicioAplicacao;
    }

    /**
     * @param exercicioAplicacao the exercicioAplicacao to set
     */
    public void setExercicioAplicacao(ExercicioAplicacao exercicioAplicacao) {
        this.exercicioAplicacao = exercicioAplicacao;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
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
        final TreinoAplicacao other = (TreinoAplicacao) obj;
        return this.id == other.id;
    }


    @Override
    public String toString() {
        return "\n ACADEMIA: " + academia.getNome() + "\n" 
                + " FICHA DE TREINO: "
                + "\n" + " Aluno(a): " + pessoa.getNome() 
                + "\n" + " DIVISAO DE TREINO: " + divisaoTreino.getNome()
                + "\n" + " INICIO: " + treino.getDataInicio()
                + " TERMINO: " + treino.getDataTermino()
                + " - " + treino.retornaPeriodo() + " semanas"
                + "\n" + " " + divisaoTreinoMusculo.getDescricao()
                + "\n" + " " + exercicio.getNome()
                + "  "  + exercicioAplicacao.getDescricao();
                
                
    }

}
