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
public class AvaliacaoFisica {

    private int id;
    private static int serial;
    private Pessoa pessoa;
    private Treino ultimoTreino;
    private double peso;
    private double altura;
    private double imc;
    private double indiceSatisfacao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public AvaliacaoFisica() {
        AvaliacaoFisica.serial++;
        this.id = serial;
    }

    public int getId() {
        return id;
    }

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
     * @return the ultimoTreino
     */
    public Treino getUltimoTreino() {
        return ultimoTreino;
    }

    /**
     * @param ultimoTreino the ultimoTreino to set
     */
    public void setUltimoTreino(Treino ultimoTreino) {
        this.ultimoTreino = ultimoTreino;
    }

    /**
     * @return the peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * @return the altura
     */
    public double getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * @return the indiceSatisfacao
     */
    public double getIndiceSatisfacao() {
        return indiceSatisfacao;
    }

    /**
     * @param indiceSatisfacao the indiceSatisfacao to set
     */
    public void setIndiceSatisfacao(double indiceSatisfacao) {
        this.indiceSatisfacao = indiceSatisfacao;
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

    /**
     * @return the imc
     */
    public double getImc() {
        return imc;
    }

    public void setImc() {
        imc = getPeso() / (getAltura() * getAltura());
        this.imc = imc;
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
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
        final AvaliacaoFisica other = (AvaliacaoFisica) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "AvaliacaoFisica{" + "id=" + id + ", pessoa=" + pessoa + ", peso=" + peso + ", altura=" + altura + ", imc=" + imc + ", indiceSatisfacao=" + indiceSatisfacao + ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao + '}';
    }
    
}
