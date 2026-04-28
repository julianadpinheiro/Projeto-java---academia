package mvc.model;

import static java.lang.Math.random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juliana
 */
public class Academia {

    private Random random = new Random();
    //colocar vetor 
    //usar local date.
    //=>CRUD de ACADEMIA. Informações importantes: id, nome, endereço, dataCriacao, dataModificacao.
    //aqui vai as strings e os metodos gets e sets
    private int id = random.nextInt(1001);
    private String nome;
    private String endereco;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private LocalDateTime dataModificacao = LocalDateTime.now();

    public Academia(){
        this.id = random.nextInt(1001);
    }
    
    public int setId(int id) {
        return id;
    }

    public int getId() {
        return id;
    }

 
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public LocalDateTime getDataCriacao(){
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao){
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
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
        final Academia other = (Academia) obj;
        return this.id == other.id;
    }

    
    @Override
  public String toString() {
        return "ID: " + id + "\n" + " Nome: " + nome
                        + "\n" + "Endereco: " + endereco + "\n" + " " + Util.getDataCriacao()
                        + "\n" + " " + Util.getDataModificacao();
    }
}
                                                                                        