/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

/**
 *
 * @author luisrobinson
 */
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmi.Servico;
import rmi.ServicoListener;

public class ClienteDois implements ServicoListener{
   public static void main(String[] args) {
		try {
			String nomeServico = "ServidorDeNoticias";
			int porta = 1234;

			ServicoListener ClienteDois = new ClienteDois();
			ServicoListener clienteAdistribuido = (ServicoListener) UnicastRemoteObject.exportObject(ClienteDois, 0);

			Registry registry = LocateRegistry.getRegistry(porta);
			Servico servicoRemoto = (Servico) registry.lookup(nomeServico);
			servicoRemoto.addListener(clienteAdistribuido);

			double valor = 20;
			System.out.println("Cliente A enviando: " + valor);
			servicoRemoto.setX(valor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void calculoEfetuado(double resultado) throws RemoteException {
		System.out.println("Servidor devolveu para Cliente Dois a notícia " + resultado);
	}
}
