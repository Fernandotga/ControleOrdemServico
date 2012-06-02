/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fasa.ControleOS.presentation.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author petronio
 */
@ManagedBean(name = "teste")
@SessionScoped
public class NewJSFManagedBean {

    String input, output;
    
    public void acao(){
        output = input;
        input = "";
    }
    
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    
    
    public NewJSFManagedBean() {
    }
    
    
}
