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
public interface IRepositorio <T> {
    void salvar(T obj);
    T abrir (long id);
    void apagar (T obj);
    List<T> listarTodos();
    
}
