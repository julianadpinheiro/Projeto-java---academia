package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juliana
 */
public class Pessoa {

    //CRUD de PESSOA. Informações importantes: 
    //id, nome, sexo, nascimento, login, senha, tipoUsuario, dataCriacao, dataModificacao.
    //somente strings e metodos gets e setters
    //colocar vetor 
    //usar local date.
    private static int serial;
    private int id = 1;
    private String nome;
    private String sexo;
    private LocalDate nascimento;
    private String login;
    private String senha;
    private String tipoUsuario;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    
    public Pessoa() {
        Pessoa.serial++;
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
    
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public LocalDate getNascimento() {
        return nascimento;
    }
    
    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the tipoUsuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
        hash = 83 * hash + this.id;
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
        final Pessoa other = (Pessoa) obj;
        return this.id == other.id;
    }
    
    @Override
    public String toString() {
        return nome
                + "\n " + "ID:" + id
                + "\n " + "sexo: " + sexo
                + "\n " + "nascimento: " + Util.getDataFormatada(nascimento)
                + "\n " + "tipoUsuario: " + tipoUsuario
                + "\n " + Util.getDataCriacao()
                + "\n " + Util.getDataModificacao();
    }

   
    
}
