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
 * @author juliana
 */
public class DivisaoTreinoMusculoDAO {

    public long adicionaERetornaId(DivisaoTreinoMusculo divisaoTreinoMusculo) {
        String sql = "insert into divisaotreinomusculo "
                + "(iddivisaoTreinoMusculo,descricao,nomediv,iddivisaoTreino,descricaodiv,datacriacao,datamodificacao)" + " values (?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // seta os valores
            stmt.setInt(1, divisaoTreinoMusculo.getId());
            stmt.setString(2, divisaoTreinoMusculo.getDescricao()); 
            stmt.setString(3,divisaoTreinoMusculo.getDivisaoTreino().getNome());
            stmt.setInt(4, divisaoTreinoMusculo.getDivisaoTreino().getId());  
            stmt.setString(5,divisaoTreinoMusculo.getDivisaoTreino().getDescricao());
            stmt.setString(6, Util.getDataCriacao());
            stmt.setString(7, Util.getDataModificacao());
            stmt.execute();
            
            ResultSet rs=stmt.getGeneratedKeys();
            int retorno=0;
            if(rs.next()){
                retorno = rs.getInt(1);
            }
            return retorno;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       
    }
    
     public DivisaoTreinoMusculo buscaDivisaoPorId(int id) {
        String sql = "SELECT * FROM divisaotreinomusculo WHERE iddivisaoTreinoMusculo = ?";

        try (Connection connection = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    DivisaoTreinoMusculo divisaoTreinoMusculo = new DivisaoTreinoMusculo();
                    divisaoTreinoMusculo.setId(rs.getInt("iddivisaoTreinoMusculo"));
                    divisaoTreinoMusculo.setDescricao(rs.getString("descricaodiv"));
                    Util.getDataCriacao();
                    Util.getDataModificacao();
                    return divisaoTreinoMusculo;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    
    public boolean excluir(int id) {
        String sql = "DELETE FROM divisaotreinomusculo WHERE iddivisaoTreinoMusculo = ?";

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
        String sql = "UPDATE divisaotreinomusculo SET descricao = ? WHERE iddivisaoTreinoMusculo = ?";

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
    public List<DivisaoTreinoMusculo> lista(DivisaoTreinoMusculo elemento) {
        String sql = "select * from divisaotreinomusculo";

        List<DivisaoTreinoMusculo> divisaotreinoMusculos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("iddivisaoTreinoMusculo");
                String descricao = rs.getString("descricao");
                
                DivisaoTreinoMusculo divisaoTreinoMusculo = new DivisaoTreinoMusculo();
                divisaoTreinoMusculo.setId(id);
                divisaoTreinoMusculo.setDescricao(descricao);
                Util.getDataCriacao();
                Util.getDataModificacao();
                divisaotreinoMusculos.add(divisaoTreinoMusculo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // itera no ResultSet
        return divisaotreinoMusculos;
    }

    /*
    @Override
    public String toString() {
        String response = new String();
        
        for (int i = 0; i < divisaoTreinoMusculos.length; i++) {
            DivisaoTreinoMusculo divisaoTreinoMusculo = divisaoTreinoMusculos[i];
            if (divisaoTreinoMusculo != null) {
                DivisaoTreino divisaoTreino = divisaoTreinoMusculo.getDivisaoTreino();
                response += divisaoTreino.toString() + " - " + divisaoTreinoMusculo.toString() + "\n";
               
            }

        }
        return response;
    }
    */
     
    
}
        