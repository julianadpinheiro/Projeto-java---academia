package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

public class AcademiaDAO {

    //private Academia[] academias = new Academia[5];
    public AcademiaDAO() {
        Academia academia = new Academia();
        academia.setNome("biotech");
        academia.setEndereco("biomall");
        Util.getDataCriacao();
        Util.getDataModificacao();

        // Adiciona a academia ao banco de dados
        this.adicionar(academia);

    }

    public Academia adicionar(Academia academia) {
        String sql = "insert into academia "
                + "(idacademia,nome,endereco,datacriacao,datamodificacao)" + " values (?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            // seta os valores
            stmt.setInt(1, academia.getId());
            stmt.setString(2, academia.getNome());
            stmt.setString(3, academia.getEndereco());
            //stmt.setTimestamp(4, java.sql.Timestamp.valueOf(academia.getDataCriacao()));
            stmt.setString(4, Util.getDataCriacao());
            stmt.setString(5, Util.getDataModificacao());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return academia;
    }

    public boolean excluir(String nome) {
        String sql = "DELETE FROM academia WHERE nome = ?";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nome);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean alterar(String nome, String novoNome) {
        String sql = "UPDATE academia SET nome = ? WHERE nome = ?";

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

    public List<Academia> lista(Academia elemento) {
        String sql = "select * from academia";

        List<Academia> academias = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql); 
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                
                int id = rs.getInt("idacademia");
                String nome = rs.getString("nome");

                String endereco = rs.getString("endereco");
                
                //Date= rs.getDate("datacriacao");
                Academia academia = new Academia();
                academia.setId(id);
                academia.setNome(nome);

                academia.setEndereco(endereco);
                Util.getDataCriacao();
                Util.getDataModificacao();
                academias.add(academia);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // itera no ResultSet
        return academias;
    }

    public Academia buscaAcademiaPorNome(String nome) {
        String sql = "SELECT * FROM academia WHERE nome = ?";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Academia academia = new Academia();
                    academia.setId(rs.getInt("idacademia"));
                    academia.setNome(rs.getString("nome"));
                    academia.setEndereco(rs.getString("endereco"));
                    Util.getDataCriacao();
                    Util.getDataModificacao();
                    return academia;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Academia buscaAcademiaPorId(int id) {
        String sql = "SELECT * FROM academia WHERE idacademia = ?";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Academia academia = new Academia();
                    academia.setId(rs.getInt("idacademia"));
                    academia.setNome(rs.getString("nome"));
                    academia.setEndereco(rs.getString("endereco"));
                    Util.getDataCriacao();
                    Util.getDataModificacao();
                    return academia;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    /*
    @Override
    public String toString() {
        String response = new String();
        for (int i = 0; i < academias.length; i++) {
            Academia academia = academias[i];

            if (academia != null) {
                response += "ID: " + academia.getId() + "\n" + " Nome: " + academia.getNome()
                        + "\n" + " " + academia.getEndereco() + "\n" + util.getDataCriacao()
                        + "\n" + " " + util.getDataModificacao();
            }
        }
        return response;
    }
     */
}
