/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fasa.OS.DomainModel;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author petronio
 */
@Remote
public interface IClienteRepositorio extends IRepositorio<Cliente>{
    List<Cliente> listarPorNome(String nome);
    
}
