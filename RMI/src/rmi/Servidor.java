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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmi.Servico;

public class Servidor {
    public static void main(String args[]) {
		try {
			String nomeServico = "ServidorDeNoticias";
			int porta = 1234;

			Servico servico = new ImplementacaoServico();
			Servico servicoDistribuido = (Servico) UnicastRemoteObject.exportObject(servico, 0);

			Registry registry = LocateRegistry.createRegistry(porta);
			registry.bind(nomeServico, servicoDistribuido);
			System.out.printf("Servico disponivel: %s%n", nomeServico);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
