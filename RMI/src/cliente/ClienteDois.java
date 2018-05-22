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
import java.util.List;
import java.util.Scanner;

import crud.Noticia;
import rmi.Servico;
import rmi.ServicoListener;
import rmi.Topico;

public class ClienteDois extends UnicastRemoteObject implements ServicoListener {
    private static Scanner scanner = new Scanner(System.in);

    protected ClienteDois() throws RemoteException {
	}

	public static void main(String[] args) {
		try {
			String nomeServico = "ServidorDeNoticias";
			int porta = 1234;

			ServicoListener cliente = new ClienteDois();

			Registry registry = LocateRegistry.getRegistry(porta);
            Servico servicoRemoto = (Servico) registry.lookup(nomeServico);
            startUi(servicoRemoto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    private static void startUi(Servico servico) throws RemoteException {
        System.out.println("****** Menu Principal ******");
        System.out.println("Selecione uma das opcoes:");
        System.out.println("1 - Adicionar Topico");
        System.out.println("2 - Inserir Noticia");
        System.out.println("3 - Consutar Noticias");
        System.out.println("4 - Consutar Topicos");
        System.out.println("0 - Sair");
        int choice = scanner.nextInt();
        if(choice == 1){
            System.out.println("Insira o nome do topico:");
            scanner.nextLine();
            String nomeTopico = scanner.nextLine();
            Topico t = new Topico(nomeTopico);
            servico.adicionarTopico(new Topico(nomeTopico));
        } else if (choice == 2){
            System.out.println("Insira o topico da noticia:");
            scanner.nextLine();
            String topico = scanner.nextLine();
            System.out.println("Insira o text da noticia:");
            String texto = scanner.nextLine();
            servico.adicionarNoticia(new Noticia(texto), topico);
        } else if (choice == 3){
            List noticias = servico.getTodasNoticias();
            noticias.forEach(n -> System.out.println(((Noticia)n).getTexto()));
            System.out.println();
        } else if (choice == 4){
            System.out.println("****** Topicos ******");
            List topicos = servico.getTopicos();
            System.out.println();

            topicos.forEach(t -> {System.out.println(((Topico)t).nome);});
        } else if(choice == 0){
            return;
        }
        startUi(servico);

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
