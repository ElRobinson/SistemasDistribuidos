/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import crud.Noticia;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author luisrobinson
 */
public interface Servico extends Remote {

	void addListener(ServicoListener listener, String topico) throws RemoteException;

	Noticia getUltimaNoticia(String topico) throws RemoteException;

    List<Noticia> getNoticias(LocalDateTime start, LocalDateTime end, String nomeTopico) throws RemoteException;

    void adicionarNoticia(Noticia noticia, String topico) throws RemoteException;
}
