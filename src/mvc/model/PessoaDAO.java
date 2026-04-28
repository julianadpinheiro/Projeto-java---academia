/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


 

/**
 *
 * @author victoria.santos
 */

public class PessoaDAO {
    
    //
    private Pessoa[] pessoas = new Pessoa[5];
    
   
    
   
    
    public PessoaDAO(){
        
        
        Pessoa j1 = new Pessoa();
        LocalDate dataNascimento = LocalDate.of(1998, 6, 13);
        
       
       
        // Data de nascimento no formato dd/MM/yyyy
       
        
        j1.setNome("lola");
        j1.setLogin("lola");
        j1.setSenha("lola");
        j1.setSexo("feminino");
        j1.setTipoUsuario("administrador");
        j1.setNascimento(dataNascimento);
        Util.getDataCriacao();
        Util.getDataModificacao();
        
        
        this.adicionar(j1);
        
    }
    
   public Pessoa adicionar(Pessoa pessoa) {
    String sql = "insert into pessoa" 
            + "(idpessoa, nome, sexo, data_nascimento, login, senha, tipo_usuario,data_criacao,data_modificacao) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";

    try (Connection connection = new ConnectionFactory().getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        //setar valores
        stmt.setInt(1, pessoa.getId());
        stmt.setString(2, pessoa.getNome());
        stmt.setString(3, pessoa.getSexo());
        stmt.setObject(4, pessoa.getNascimento()); 
        stmt.setString(5, pessoa.getLogin());
        stmt.setString(6, pessoa.getSenha());
        stmt.setString(7, pessoa.getTipoUsuario());
        stmt.setObject(8, Util.getDataCriacao());
        stmt.setObject(9, Util.getDataModificacao());


        stmt.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return pessoa;
}

     
 public Pessoa buscaPessoaPorLogin(String login, String senha) {
    String sql = "SELECT * FROM pessoa WHERE login = ? AND senha = ?";
    
    try (Connection connection = new ConnectionFactory().getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
        
        stmt.setString(1, login);
        stmt.setString(2, senha);
        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            Pessoa pessoa = new Pessoa();
                    pessoa.setId(rs.getInt("idpessoa"));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setLogin(rs.getString("login"));
                    pessoa.setSenha(rs.getString("senha"));
                    pessoa.setSexo(rs.getString("sexo"));
                    pessoa.setTipoUsuario(rs.getString("tipo_usuario"));
                    pessoa.setNascimento(rs.getObject("data_nascimento", LocalDate.class));
                    Util.getDataCriacao();
                    Util.getDataModificacao();
            
            return pessoa;
        }
        
    } catch (SQLException e) {
       throw new RuntimeException(e);
    }
    
    return null;
}
     public Pessoa buscaPessoaPorId(int id) {
        String sql = "SELECT * FROM pessoa WHERE idpessoa = ?";

        try (Connection connection = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(rs.getInt("idpessoa"));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setLogin(rs.getString("login"));
                    pessoa.setSenha(rs.getString("senha"));
                    pessoa.setSexo(rs.getString("sexo"));
                    pessoa.setTipoUsuario(rs.getString("tipo_usuario"));
                    pessoa.setNascimento(rs.getObject("data_nascimento", LocalDate.class));
                    Util.getDataCriacao();
                    Util.getDataModificacao();
                    return pessoa;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    
    
    
public boolean excluir(String nome) {
        String sql = "DELETE FROM =pessoa WHERE nome = ?";

        try (Connection connection = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nome);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

public boolean alterar(String nome, String novaDescricao) {
        String sql = "UPDATE pessoa SET descricao = ? WHERE nome = ?";

        try (Connection connection = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            
            stmt.setString(1, novaDescricao);
            stmt.setString(2, nome);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                Util.setDataModificacao(LocalDateTime.now());
                return true;
            }

        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        return false;
    }

    public List<Pessoa> lista(Pessoa elemento) {
        String sql = "select * from pessoa";

        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("idpessoa");
                String nome = rs.getString("nome");

                String login = rs.getString("login");

                String sexo = rs.getString("sexo");
                String tp = rs.getString("tipo_usuario");
                LocalDate nasc = rs.getObject("data_nascimento", LocalDate.class);

                Pessoa pessoa = new Pessoa();
                pessoa.setId(id);
                pessoa.setNome(nome);

                pessoa.setLogin(login);
                pessoa.setSexo(sexo);
                pessoa.setTipoUsuario(tp);
                pessoa.setNascimento(nasc);
                Util.getDataCriacao();
                Util.getDataModificacao();
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // itera no ResultSet
        return pessoas;
    }
    
    /*
    @Override
    public String toString() {
        String response = new String();
        for (int i = 0; i < pessoas.length; i++) {
            Pessoa pessoa = pessoas[i];
            if(pessoa != null)
                response += "ID: " + pessoa.getId()+ "\n" + " Nome: " + pessoa.getNome() + "\n";
        }
        return response;
    }
    */
}
