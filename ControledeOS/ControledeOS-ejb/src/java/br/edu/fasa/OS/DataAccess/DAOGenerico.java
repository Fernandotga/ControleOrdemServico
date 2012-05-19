/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fasa.OS.DataAccess;

import br.edu.fasa.OS.DomainModel.IRepositorio;
import java.util.List;
import javax.ejb.EntityContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author petronio
 */
public abstract class DAOGenerico <T> implements IRepositorio <T> {
    
    public DAOGenerico(Class tip){
        tipo=tip;
    }
    
    @PersistenceContext(name="ControledeOS-ejbPU")
    protected EntityManager manager;
    private Class tipo;

    @Override
    public void salvar(T obj) {
        manager.persist(obj);
    }

    @Override
    public T abrir(long id) {
        return (T) manager.find(tipo, id);
    }

    @Override
    public void apagar(T obj) {
        manager.remove(obj);
    }

    @Override
    public abstract List<T> listarTodos(); 
    
}
