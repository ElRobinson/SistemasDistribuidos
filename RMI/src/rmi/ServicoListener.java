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
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicoListener extends Remote {

	void calculoEfetuado(double resultado) throws RemoteException;
}
