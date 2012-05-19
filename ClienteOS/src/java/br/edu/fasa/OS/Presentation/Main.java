/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fasa.OS.Presentation;

import br.edu.fasa.OS.DomainModel.Cliente;
import br.edu.fasa.OS.DomainModel.IClienteRepositorio;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author petronio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Context context= (Context) new InitialContext();
            IClienteRepositorio ejb=(IClienteRepositorio) context.lookup("java:global/ControledeOS/ControledeOS-ejb/ClienteDAO");
            // TODO code application logic here
            Cliente cliente = new Cliente();
            cliente.setNome("Nayara");
            cliente.setCpf("0910927838");
            ejb.salvar(cliente);
        } catch (NamingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
