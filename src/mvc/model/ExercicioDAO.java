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
public class ExercicioDAO {

   
    public ExercicioDAO(){
        Exercicio j1 = new Exercicio();
        Exercicio j2 = new Exercicio();
        j1.setNome("supino reto");
        j2.setNome("agachamento livre");
        j1.setDescricao("deitar no banco do equipamento");
        j2.setDescricao("Mantenha as pernas levemente afastadas");
        
        this.adicionar(j1);
        this.adicionar(j2);
        
    }

   public Exercicio buscaExercicioPorNome(String nome) {
        String sql = "SELECT * FROM exercicio WHERE nome = ?";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Exercicio exercicio = new Exercicio();
                    exercicio.setId(rs.getInt("idexercicio"));
                    exercicio.setNome(rs.getString("nome"));
                    exercicio.setDescricao(rs.getString("descricao"));
                    Util.getDataCriacao();
                    Util.getDataModificacao();
                    return exercicio;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
   
   public Exercicio buscaExercicioPorId(int id) {
        String sql = "SELECT * FROM exercicio WHERE idexercicio = ?";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Exercicio exercicio = new Exercicio();
                    exercicio.setId(rs.getInt("idexercicio"));
                    exercicio.setNome(rs.getString("nome"));
                    exercicio.setDescricao(rs.getString("descricao"));
                    Util.getDataCriacao();
                    Util.getDataModificacao();
                    return exercicio;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

     public Exercicio adicionar(Exercicio exercicio) {
    String sql = "insert into exercicio" 
            + "(idexercicio, nome,descricao,datacriacao, datamodificacao) VALUES (?, ?, ?, ?, ?)";

    try (Connection connection = new ConnectionFactory().getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        //setar valores
        stmt.setInt(1, exercicio.getId());
        stmt.setString(2, exercicio.getNome());
        stmt.setString(3, exercicio.getDescricao());
        stmt.setString(4, Util.getDataCriacao());
        stmt.setString(5, Util.getDataModificacao());
        


        stmt.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return exercicio;
}

    public boolean excluir(String nome) {
        String sql = "DELETE FROM =exercicio WHERE nome = ?";

        try (Connection connection = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nome);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public boolean alterar(String nome, String novoNome) {
        String sql = "UPDATE exercicio SET nome = ? WHERE nome = ?";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
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
    
    public List<Exercicio> lista(Exercicio elemento) {
        String sql = "select * from exercicio";

        List<Exercicio> exercicios = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql); 
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                
                int id = rs.getInt("idexercicio");
                String nome = rs.getString("nome");

                String descricao = rs.getString("descricao");
                
                //Date= rs.getDate("datacriacao");
                Exercicio exercicio = new Exercicio();
                exercicio.setId(id);
                exercicio.setNome(nome);

                exercicio.setDescricao(descricao);
                Util.getDataCriacao();
                Util.getDataModificacao();
                exercicios.add(exercicio);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // itera no ResultSet
        return exercicios;
    }

    /*
    @Override
    public String toString() {
        String response = new String();
        for (int i = 0; i < exercicios.length; i++) {
            Exercicio exercicio = exercicios[i];
            if (exercicio != null) {
                response +="\n"+ "ID: " + exercicio.getId() + " Nome: " + exercicio.getNome() 
                        + " = " + exercicio.getDescricao() + "\n" + util.getDataCriacao() 
                        + "\n" + util.getDataModificacao();
            }
        }
        return response;
    }
*/
}
