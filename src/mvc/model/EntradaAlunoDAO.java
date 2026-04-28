/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author juliana
 */
public class EntradaAlunoDAO {
    
    private EntradaAluno[] entradaAlunos = new EntradaAluno[5];

   public EntradaAluno buscaExercicioPorId(int id) {
        for (EntradaAluno j : entradaAlunos) {
            if (j != null && j.getId()==id) {
                return j;
            }
        }

        return null;
    }

    public boolean adicionar(EntradaAluno entradaAluno) {
        for (int i = 0; i < entradaAlunos.length; i++) {
            if (entradaAlunos[i] == null) {
                entradaAlunos[i] = entradaAluno;
                return true;
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        for (int i = 0; i < entradaAlunos.length; i++) {
            if (entradaAlunos[i] != null && entradaAlunos[i].getId()==id) {
               entradaAlunos[i] = null;
                return true;
            }

        }
        return false;
    }
    
    public double metodoVeMensalidade(AlunoPagamentoMensalidadeDAO alunoPagamentoMensalidadeDAO){
         AlunoPagamentoMensalidade[] alunoPagamentoMensalidades = alunoPagamentoMensalidadeDAO.getAlunoPagamentoMensalidades();
        for (AlunoPagamentoMensalidade alunoPagamentoMensalidade : alunoPagamentoMensalidades) {
           Double pago = alunoPagamentoMensalidade.getValor_pago();

            return pago;
        }
        return 0;
    }
        
    
    @Override
    public String toString() {
        String response = new String();
        for (int i = 0; i < entradaAlunos.length; i++) {
            EntradaAluno entradaAluno = entradaAlunos[i];
            if (entradaAluno != null) {
                response += "ID: " + entradaAluno.getId();
            }
        }
        return response;
    }
}
