/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 *
 * @author luisrobinson
 */
public class Noticia implements Serializable {

    public int id;
    public String texto;
    public LocalDateTime data = LocalDateTime.now();

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

    @Override
    public String toString() {
        return "Noticia{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", data=" + data +
                '}';
    }
}
