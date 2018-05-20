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

import rmi.Servico;
import rmi.ServicoListener;

class ImplementacaoServico implements Servico {

	private final List<ServicoListener> listeners = new ArrayList<>();

	private boolean setouX;
	private boolean setouY;

	private double valorX;
	private double valorY;

	@Override
	public void addListener(ServicoListener listener) throws RemoteException {
		listeners.add(listener);
	}

	@Override
	public void setX(double valor) throws RemoteException {
		valorX = valor;
		setouX = true;
		System.out.println("saldkfmoaisdfioasdf");
		verifica();
	}

	@Override
	public void setY(double valor) throws RemoteException {
		valorY = valor;
		setouY = true;
		verifica();
	}

	private void verifica() {
		if (setouX && setouY) {
			double resultado = valorX + valorY;
			for (ServicoListener listener : listeners) {
				try {
					listener.calculoEfetuado(resultado);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			setouX = false;
			setouY = false;
		}
	}
}