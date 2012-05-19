/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fasa.OS.DomainModel;

import java.util.List;

/**
 *
 * @author petronio
 */
public interface IServicoRepositorio extends IRepositorio <Servico>{
    List<Servico> listarPorDescricao(String descricao);
    List<Servico> listarPrecoMenorQue(float valor);
    
}
