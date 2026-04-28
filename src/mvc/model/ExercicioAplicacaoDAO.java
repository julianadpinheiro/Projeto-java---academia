/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juliana
 */
public class ExercicioAplicacaoDAO {
    //private ExercicioAplicacao[] exerciciosAplicacao = new ExercicioAplicacao[5];
    
    public ExercicioAplicacaoDAO(){
        ExercicioAplicacao j1 = new ExercicioAplicacao();
        ExercicioAplicacao j2 = new ExercicioAplicacao();
        j1.setDescricao("4x12");
        j2.setDescricao("4x10");
        
        this.adicionar(j1);
        this.adicionar(j2);
        
    }
   
   public ExercicioAplicacao adicionar(ExercicioAplicacao exercicioaplicacao) {
        String sql = "insert into exercicioaplicacao "
                + "(idexercicioaplicacao,descricao,datacriacao,datamodificacao)" + " values (?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            // seta os valores
            stmt.setInt(1, exercicioaplicacao.getId());
            stmt.setString(2, exercicioaplicacao.getDescricao());
            
            stmt.setString(3, Util.getDataCriacao());
            stmt.setString(4, Util.getDataModificacao());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exercicioaplicacao;
    }
    
    public boolean excluir(int id) {
        String sql = "DELETE FROM exercicioaplicacao WHERE idexercicioaplicacao = ?";

        try (Connection connection = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean alterar(int id, String novaDescricao) {
        String sql = "UPDATE exercicioaplicacao SET descricao = ? WHERE idexercicioaplicacao = ?";

        try (Connection connection = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            
            stmt.setString(1, novaDescricao);
            stmt.setInt(2, id);
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
    
    public ExercicioAplicacao buscaExercicioAplicacaoPorId(int id) {
        String sql = "SELECT * FROM exercicioaplicacao WHERE idexercicioaplicacao = ?";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ExercicioAplicacao exercicioaplicacao = new ExercicioAplicacao();
                    exercicioaplicacao.setId(rs.getInt("idexercicioaplicacao"));
                    exercicioaplicacao.setDescricao(rs.getString("descricao"));
                    
                    Util.getDataCriacao();
                    Util.getDataModificacao();
                    return exercicioaplicacao;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    public List<ExercicioAplicacao> lista(ExercicioAplicacao elemento) {
        String sql = "select * from exercicioaplicacao";

        List<ExercicioAplicacao> exerciciosaplicacao = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                
                int id = rs.getInt("idexercicioaplicacao");
                
                String descricao = rs.getString("descricao");

                

                //Date= rs.getDate("datacriacao");
                ExercicioAplicacao exercicioaplicacao = new ExercicioAplicacao();
                exercicioaplicacao.setId(id);
                exercicioaplicacao.setDescricao(descricao);
                Util.getDataCriacao();
                Util.getDataModificacao();
                exerciciosaplicacao.add(exercicioaplicacao);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // itera no ResultSet
        return exerciciosaplicacao;
    }
    
    /*
    @Override
    
    public String toString() {
        String response = new String();
        for (int i = 0; i < exerciciosAplicacao.length; i++) {
            ExercicioAplicacao exercicioAplicacao = exerciciosAplicacao[i];
            if(exercicioAplicacao != null)
                response += "\n"+ "ID: " + exercicioAplicacao.getId() 
                        + "\n" + " Descricao: " + exercicioAplicacao.getDescricao()
                        + "\n" + util.getDataCriacao()
                        + "\n" + util.getDataModificacao();
        }
        return response;
    }
*/

}