/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juliana
 */
public class TreinoDAO {

    
   public long adicionaERetornaId(Treino treino) {
        String sql = "insert into treino "
                + "(idtreino,objetivo,datainicio,datatermino,nomedivisaotreino,iddivisaotreino,descricaodiv,datacriacao,datamodificacao)" + " values (?,?,?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // seta os valores
            stmt.setInt(1, treino.getId());
            stmt.setString(2, treino.getObjetivo());
            stmt.setDate(3, java.sql.Date.valueOf(treino.getDataInicio()));
            stmt.setDate(4, java.sql.Date.valueOf(treino.getDataTermino()));
            stmt.setString(5, treino.getDivisaoTreino().getNome());
            stmt.setInt(6, treino.getDivisaoTreino().getId());
            stmt.setString(7, treino.getDivisaoTreino().getDescricao()); 
            stmt.setString(8, Util.getDataCriacao());
            stmt.setString(9, Util.getDataModificacao());
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

   public Treino buscaTreinoPorId(int id) {
        String sql = "SELECT * FROM treino WHERE idtreino = ?";
        

        try (Connection connection = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
                
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Treino treino = new Treino();
                    
                    treino.setId(rs.getInt("idtreino"));
                    treino.setObjetivo("objetivo");
                    treino.setDataInicio(LocalDate.MIN);
                    treino.setDataTermino(LocalDate.MIN);
          
                    //Util.getDataFormatada(treino.getDataTermino());
                    Util.getDataCriacao();
                    Util.getDataModificacao();
                    return treino;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

   public boolean excluir(int id) {
        String sql = "DELETE FROM =treino WHERE idtreino = ?";

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
        String sql = "UPDATE treino SET descricao = ? WHERE idtreino = ?";

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
  public List<Treino> lista(Treino elemento) {
        String sql = "select * from treino";

        List<Treino> treinos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("idtreino");
                
                String objetivo= rs.getString("objetivo");
                
                Treino treino = new Treino();
                treino.setId(id);
               
                treino.setObjetivo(objetivo);
                treino.setDataInicio(LocalDate.now());
                 treino.setDataTermino(LocalDate.now());
                Util.getDataCriacao();
                Util.getDataModificacao();
                treinos.add(treino);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // itera no ResultSet
        return treinos;
    }
   /*

    @Override
    public String toString() {
        String response = new String();
        for (int i = 0; i < treinos.length; i++) {
            Treino treino = treinos[i];
            
            if (treino != null) {
                DivisaoTreino divisaoTreino = treino.getDivisaoTreino();
                response += divisaoTreino.toString() +" ID: " + treino.getId() + "=  " + treino.getObjetivo()
                        + "\n" + " DataI: " + treino.getDataInicio() + " DataF: " + treino.getDataTermino()
                        + "\n";
            }
        }
        return response;
    }
*/
}
