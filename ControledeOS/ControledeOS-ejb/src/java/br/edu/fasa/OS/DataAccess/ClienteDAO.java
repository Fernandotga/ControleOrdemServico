/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fasa.OS.DataAccess;

import br.edu.fasa.OS.DomainModel.Cliente;
import br.edu.fasa.OS.DomainModel.IClienteRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author petronio
 */
@Stateless
public class ClienteDAO extends DAOGenerico <Cliente> implements IClienteRepositorio{

    public ClienteDAO(){
        super(Cliente.class);
    }
    @Override
    public List<Cliente> listarTodos() {
        Query sql=(Query) manager.createQuery("select c from Cliente c order by c.nome");
        return sql.getResultList();
    }

    @Override
    public List<Cliente> listarPorNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
