/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fasa.OS.presentation.web;

import br.edu.fasa.OS.DomainModel.IClienteRepositorio;
import br.edu.fasa.OS.DomainModel.Cliente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author petronio
 */
@Named(value = "cliente")
@SessionScoped
public class ClienteBean implements Serializable {

    /**
     * Creates a new instance of ClienteBean
     */
    @EJB
    IClienteRepositorio repo;
    
    String codigo, nome, cpf;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void abrir() {
        // Converte o texto do código para um long
        Long id = Long.parseLong( codigo );
        
        // Abre o id do banco de dados
        Cliente cli = repo.abrir(id);
        
        // Nome do cliente e põe na variável de saída
        nome = cli.getNome();
        cpf = cli.getCpf();
    }
    
    public void salvar() {
        
        // Cria novo objeto de cliente
        Cliente cli = new Cliente();
        
        // Passa os dados dos campos para o objeto
        cli.setNome(nome);
        cli.setCpf(cpf);
        
        // Passa o objeto para o repositório
        repo.salvar(cli);
        
        // Atualiza o valor do código do objeto
        codigo = cli.getId().toString();
    }
    
    public ClienteBean() {
    }
}
