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
    public Noticia noticia;

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public Noticia(String texto, Noticia noticia) {
        this.texto = texto;
        this.noticia = noticia;
        id += 1;
    }
    
    
    
}
