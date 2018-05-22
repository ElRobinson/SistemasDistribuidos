package rmi;

import crud.Noticia;

import java.util.ArrayList;
import java.util.List;

public class Topico {
    public String nome;
    public List<Noticia> noticias = new ArrayList<>();
    public List<ServicoListener> listeners = new ArrayList<>();
}
