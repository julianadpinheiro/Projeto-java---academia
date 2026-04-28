/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victoria
 */
    public class DivisaoTreinoDAO {
    
    
     public DivisaoTreinoDAO(){
        DivisaoTreino j1 = new DivisaoTreino();
        
        j1.setNome("ABC");
        j1.setDescricao("ABC 2x e descansa 1x");
        
        this.adicionaERetornaId(j1);
        
    }
    
       public long adicionaERetornaId(DivisaoTreino divisaoTreino) {
        String sql = "insert into divisaotreino "
                + "(iddivisaoTreino,nome,descricao,datacriacao,datamodificacao)" + " values (?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // seta os valores
            stmt.setInt(1, divisaoTreino.getId());
            stmt.setString(2, divisaoTreino.getNome());
            stmt.setString(3, divisaoTreino.getDescricao());
            stmt.setString(4, Util.getDataCriacao());
            stmt.setString(5, Util.getDataModificacao());
            stmt.execute();
            
            ResultSet rs=stmt.getGeneratedKeys();
            
            int retorno=0;
            if(rs.next()){
                retorno = rs.getInt(3);
            }
            return retorno;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
       public String retornaNome(int id) {
        String sql = "SELECT * FROM divisaotreino WHERE iddivisaotreino = ?";

        try (Connection connection = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    DivisaoTreino divisaoTreino = new DivisaoTreino();
                    return rs.getString("nome");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    

     public DivisaoTreino buscaDivisaoPorId(int id) {
        String sql = "SELECT * FROM divisaotreino WHERE iddivisaotreino = ?";

        try (Connection connection = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    DivisaoTreino divisaoTreino = new DivisaoTreino();
                    divisaoTreino.setId(rs.getInt("iddivisaotreino"));
                    divisaoTreino.setNome(rs.getString("nome"));
                    divisaoTreino.setDescricao(rs.getString("descricao"));
                    Util.getDataCriacao();
                    Util.getDataModificacao();
                    return divisaoTreino;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    
    public boolean excluir(int id) {
        String sql = "DELETE FROM divisaotreino WHERE iddivisaotreino = ?";

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
        String sql = "UPDATE divisaotreino SET descricao = ? WHERE iddivisaotreino = ?";

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
      public boolean alterarNome(int idA, String nome) {
        String sql = "UPDATE divisaotreino SET nome = ? WHERE iddivisaotreino = ?";

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
           
            stmt.setString(1, nome);
            stmt.setInt(2, idA);
            //stmt.setString(2, descricao);
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
      
     public List<DivisaoTreino> lista(DivisaoTreino elemento) {
        String sql = "select * from divisaotreino";

        List<DivisaoTreino> divisaotreinos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("iddivisaotreino");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                
                DivisaoTreino divisaoTreino = new DivisaoTreino();
                divisaoTreino.setId(id);
                divisaoTreino.setNome(nome);
                divisaoTreino.setDescricao(descricao);
                Util.getDataCriacao();
                Util.getDataModificacao();
                divisaotreinos.add(divisaoTreino);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // itera no ResultSet
        return divisaotreinos;
    }
    
    
     /*
    @Override
    public String toString() {
        String response = new String();
        for (int i = 0; i < divisaoTreinos.length; i++) {
            DivisaoTreino divisaoTreino = divisaoTreinos[i];
            if(divisaoTreino != null)
                response += "\n" + "Treino " + divisaoTreino.getNome()+ "(id= " + divisaoTreino.getId() + ")"
                        + " =" + divisaoTreino.getDescricao()+ "\n"
                        + "\n" + util.getDataCriacao() + "\n" + util.getDataModificacao();
        }
        return response;
    }
*/
}

