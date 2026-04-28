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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author victoria.santos
 */
public class TreinoAplicacaoDAO {
    
    
     public TreinoAplicacao buscaTreinoAplicacaoPorId(int id) {
        String sql = "SELECT * FROM treinoaplicacao WHERE idtreinoaplicacao = ?";

        try (Connection connection = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    TreinoAplicacao treinoaplicacao = new TreinoAplicacao();
                    treinoaplicacao.setId(rs.getInt("idtreinoaplicacao"));
                   
                    Util.getDataCriacao();
                    Util.getDataModificacao();
                    return treinoaplicacao;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public long adicionaERetornaId(TreinoAplicacao treinoaplicacao) {
        String sql = "insert into treinoaplicacao "
                + "(idtreinoaplicacao,academia,pessoa,divtreino,treinoinic,treinotermino,divtreinomusc,exercicio,exercicioapl,datacriacao,datamodificacao)" + " values (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
           
            // seta os valores
            stmt.setInt(1, treinoaplicacao.getId());
            stmt.setString(2, treinoaplicacao.getAcademia().getNome());
            stmt.setString(3, treinoaplicacao.getPessoa().getNome());
            stmt.setString(4, treinoaplicacao.getDivisaoTreino().getNome());
            stmt.setDate(5, java.sql.Date.valueOf(treinoaplicacao.getTreino().getDataInicio()));
            stmt.setDate(6, java.sql.Date.valueOf(treinoaplicacao.getTreino().getDataTermino()));
            stmt.setString(7, treinoaplicacao.getDivisaoTreinoMusculo().getDescricao());
            stmt.setString(8, treinoaplicacao.getExercicio().getNome());
            stmt.setString(9, treinoaplicacao.getExercicioAplicacao().getDescricao());
            stmt.setString(10, Util.getDataCriacao());
            stmt.setString(11, Util.getDataModificacao());
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
    
   public boolean excluir(int id) {
        String sql = "DELETE FROM =treinoaplicacao WHERE idtreinoplicacao = ?";

        try (Connection connection = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
   public List<TreinoAplicacao> lista(TreinoAplicacao elemento) {
        String sql = "select * from treinoaplicacao";

        List<TreinoAplicacao> treinoaplicacao = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("idtreinoaplicacao");
                String nome = rs.getString("academia");
                String nomep = rs.getString("pessoa");
                String nomediv = rs.getString("divtreino");
                String inicio = rs.getString("treinoinic");
                String termino = rs.getString("treinotermino");
                String descricaomusc = rs.getString("divtreinomusc");
                String nomeex = rs.getString("exercicio");
                String descricao = rs.getString("exercicioapl");
                
                //String descricao = rs.getString("descricao");
                
                TreinoAplicacao treinoAplicacao = new TreinoAplicacao();
                treinoAplicacao.setId(id);
                Util.getDataCriacao();
                Util.getDataModificacao();
                
                
                Academia academia = new Academia();
                academia.setNome(nome);
                treinoAplicacao.setAcademia(academia);
                
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(nomep);
                treinoAplicacao.setPessoa(pessoa);
                
                DivisaoTreino divisaoTreino = new DivisaoTreino();
                divisaoTreino.setNome(nomediv);
                treinoAplicacao.setDivisaoTreino(divisaoTreino);
                
                Treino treino = new Treino();
                treino.setDataInicio(LocalDate.now());
                treino.setDataTermino(LocalDate.now());
                treinoAplicacao.setTreino(treino);
                
                DivisaoTreinoMusculo divisaoTreinoMusculo = new DivisaoTreinoMusculo();
                divisaoTreinoMusculo.setDescricao(descricaomusc);
                treinoAplicacao.setDivisaoTreinoMusculo(divisaoTreinoMusculo);
                
                Exercicio exercicio = new Exercicio();
                exercicio.setNome(nomeex);
                treinoAplicacao.setExercicio(exercicio);
                
                ExercicioAplicacao exercicioAplicacao = new ExercicioAplicacao();
                exercicioAplicacao.setDescricao(descricao);
                treinoAplicacao.setExercicioAplicacao(exercicioAplicacao);
                
                treinoaplicacao.add(treinoAplicacao);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // itera no ResultSet
        return treinoaplicacao;
    }
    
}

   /*
    @Override
    public String toString() {
        String response = new String();
        for (int i = 0; i < treinosAplicacao.length; i++) {
            TreinoAplicacao treinoAplicacao = treinosAplicacao[i];
            if(treinoAplicacao != null)
                response += treinoAplicacao.toString();
        }
        return response;
    }
    */
