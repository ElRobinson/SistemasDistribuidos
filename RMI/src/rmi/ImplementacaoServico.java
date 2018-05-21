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
import java.util.ArrayList;
import java.util.List;

import crud.Noticia;
import rmi.Servico;
import rmi.ServicoListener;

class ImplementacaoServico implements Servico {

	private final List<ServicoListener> listeners = new ArrayList<>();
	private final List<Noticia> noticias = new ArrayList<>();


	@Override
	public void addListener(ServicoListener listener) throws RemoteException {
		listeners.add(listener);
	}

    @Override
    public void getNoticia() {
	    Noticia noticia;
	    if(noticias.size() == 0){
	        noticia = null;
        } else {
            noticia = noticias.get(noticias.size() - 1);
        }

        for (ServicoListener listener : listeners) {
            try {
                listener.noticiaRecebida(new Noticia("Test"));
//                listener.noticiaRecebida(noticia);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}