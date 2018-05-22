/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

/**
 *
 * @author luisrobinson
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import crud.Noticia;
import rmi.Servico;
import rmi.ServicoListener;

public class ImplementacaoServico extends UnicastRemoteObject implements Servico {

    private List<Topico> topicos = new ArrayList<>();
    private Noticia ultimaNoticia;


    public ImplementacaoServico() throws RemoteException {
        mockTopicos();
    }


    @Override
	public void addListener(ServicoListener listener, String topico) throws RemoteException {
        Topico topico1 = topicos.stream().filter(t -> t.nome.equals(topico)).findFirst().get();
        topico1.listeners.add(listener);
	}

    @Override
    public Noticia getUltimaNoticia() throws RemoteException {
	    return ultimaNoticia;
    }
    private void mockTopicos(){
        Noticia n1 = new Noticia("N1");
        n1.data = n1.data.plusDays(10);
        Noticia n2= new Noticia("N2");

        Topico topico = new Topico();
        topico.nome = "Topico 1";
        topico.noticias.add(n1);
        topico.noticias.add(n2);
        ultimaNoticia = n2;

        topicos.add(topico);
    }

    @Override
    public List<Noticia> getNoticias(LocalDateTime start, LocalDateTime end, String nomeTopico) throws RemoteException{
        return topicos.stream()
                .filter(topico -> topico.nome.equals(nomeTopico))
                .flatMap(topico -> topico.noticias.stream())
                .flatMap(n -> Stream.of(n.data)
                        .filter(start::isBefore)
                        .filter(end::isAfter)
                        .map(d -> n))
                .sorted(Comparator.comparing(n -> n.data))
                .collect(Collectors.toList());
    }

    @Override
    public void adicionarNoticia(Noticia noticia, String topico) throws RemoteException {
        Topico topico1 = topicos.stream()
                .filter(t -> t.nome.equals(topico)).findFirst().get();
        topico1.noticias.add(noticia);
        topico1.listeners.forEach(l -> {
            try {
                l.noticiaRecebida(noticia);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

}