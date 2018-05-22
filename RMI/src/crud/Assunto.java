/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

/**
 *
 * @author luisrobinson
 */
public class Assunto {
    public String Assunto;
    public int id;

    public String getAssunto() {
        return Assunto;
    }

    public void setAssunto(String Assunto) {
        this.Assunto = Assunto;
    }

    public int getId() {
        return id;
    }

    public Assunto(String Assunto) {
        this.Assunto = Assunto;
        id += 1;
    }
    
}
