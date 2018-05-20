/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author luisrobinson
 */
public interface Servico extends Remote {

	void addListener(ServicoListener listener) throws RemoteException;

	void setX(double valor) throws RemoteException;

	void setY(double valor) throws RemoteException;
}
