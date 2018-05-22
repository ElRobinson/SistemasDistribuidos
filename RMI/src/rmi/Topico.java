package rmi;

import crud.Noticia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Topico implements Serializable {
    public String nome;
    public List<Noticia> noticias = new ArrayList<>();
    public List<ServicoListener> listeners = new ArrayList<>();

    public Topico(){}
    public Topico(String nome){
        this.nome = nome;
    }
}
