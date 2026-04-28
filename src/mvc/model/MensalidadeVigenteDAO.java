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

/**
 *
 * @author victoria.santos
 */
public class MensalidadeVigenteDAO {
    private MensalidadeVigente[] mensalidadesVigentes = new MensalidadeVigente[5];
    
     
    public MensalidadeVigenteDAO(){
        MensalidadeVigente j1 = new MensalidadeVigente();
        j1.setValor(120);
        LocalDate dataInicio = LocalDate.of(2024, 5, 19);
        j1.setInicio(dataInicio);
        LocalDate dataFim = LocalDate.of(2024, 6, 19);
        j1.setTermino(dataFim);
        this.adicionar(j1);
        
    }
   
    public MensalidadeVigente buscaMensalidadePorId(int id) {
        String sql = "SELECT * FROM mensalidadevigente WHERE idmensalidadevigente = ?";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    MensalidadeVigente mensalidadeVigente = new MensalidadeVigente();
                    mensalidadeVigente.setId(rs.getInt("idmensalidadevigente"));
                    mensalidadeVigente.setValor(rs.getDouble("valor"));
                    mensalidadeVigente.setInicio(LocalDate.now());
                    mensalidadeVigente.setTermino(LocalDate.now());
                    Util.getDataCriacao();
                    Util.getDataModificacao();
                    
                    return mensalidadeVigente;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    public MensalidadeVigente adicionar(MensalidadeVigente mensalidadeVigente) {
        String sql = "insert into mensalidadevigente "
                + "(idmensalidadevigente,valor,datainicio,datatermino,datacriacao,datamodificacao)" + " values (?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            // seta os valores
            stmt.setInt(1, mensalidadeVigente.getId());
            stmt.setDouble(2, mensalidadeVigente.getValor());
            //stmt.setDate(3, mensalidadeVigente.getInicio());
            stmt.setString(3, Util.getDataFormatada(mensalidadeVigente.getInicio()));
            stmt.setString(4, Util.getDataFormatada(mensalidadeVigente.getTermino()));
            //stmt.setTimestamp(3, java.sql.Timestamp.valueOf(academia.getDataCriacao()));
            stmt.setString(5, Util.getDataCriacao());
            stmt.setString(6, Util.getDataModificacao());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mensalidadeVigente;
    }
    
    public boolean excluir(int id) {
        String sql = "DELETE FROM mensalidadevigente WHERE idmensalidadevigente = ?";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
     public boolean alterar(int id, double valor) {
        String sql = "UPDATE mensalidade SET valor = ? WHERE idmensalidadevigente = ?";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDouble(1, valor);
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
     /*
    
    @Override
    public String toString() {
        String response = new String();
        for (int i = 0; i < mensalidadesVigentes.length; i++) {
            MensalidadeVigente mensalidadeVigente = mensalidadesVigentes[i];
            if(mensalidadeVigente != null)
                response += "ID: " + mensalidadeVigente.getId() + " Valor: " + mensalidadeVigente.getValor()+ "\n";
        }
        return response;
    }
*/

   
}
