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
public class Noticia {
    public int id;
    public String texto;

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }


    public Noticia(String texto) {
        this.texto = texto;
        id += 1;
    }
    
    
    
}
