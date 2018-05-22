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
import java.util.Scanner;

import crud.Noticia;
import rmi.Servico;
import rmi.ServicoListener;

public class ClienteDois extends UnicastRemoteObject implements ServicoListener {
    private static Scanner scanner = new Scanner(System.in);

    protected ClienteDois() throws RemoteException {
	}

	public static void main(String[] args) {
		try {
			String nomeServico = "ServidorDeNoticias";
			int porta = 1234;

			ServicoListener cliente = new ClienteDois();
//
			Registry registry = LocateRegistry.getRegistry(porta);
			Servico servicoRemoto = (Servico) registry.lookup(nomeServico);
			startUi(servicoRemoto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    private static void startUi(Servico servico) {

        System.out.println("Selecione uma das opcoes:");
        System.out.println("1 - Adicionar Topico");
        System.out.println("2 - Inserir Noticia");
        System.out.println("3 - Consutar Noticias");
        System.out.println("0 - Sair");
        int choice = scanner.nextInt();
        if(choice == 1){
            servico.
        }

    }

    @Override
	public void noticiaRecebida(Noticia noticia) throws RemoteException {

	}
//
//	@Override
//	public void calculoEfetuado(double resultado) throws RemoteException {
//		System.out.println("Servidor devolveu para Cliente Dois a notícia " + resultado);
//	}
}
