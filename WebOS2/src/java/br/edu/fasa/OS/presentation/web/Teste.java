/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fasa.OS.presentation.web;


import br.edu.fasa.OS.DomainModel.Cliente;
import br.edu.fasa.OS.DomainModel.IClienteRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;


/**
 *
 * @author petronio
 */
@Named(value = "teste")
@SessionScoped
public class Teste implements Serializable {

    @EJB
    IClienteRepositorio repo;
    
    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    
    String entrada, saida;
    
    public Teste() {
    }
    
    public void fazAlgumaCoisa(){
        
        // Converte o texto do código para um long
        Long id = Long.parseLong( entrada);
        
        // Abre o id do banco de dados
        Cliente cli = repo.abrir(id);
        
        // Nome do cliente e põe na variável de saída
        saida = cli.getNome();
    }
}
