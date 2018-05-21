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
import java.io.IOException;
import java.net.InetSocketAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import com.sun.net.httpserver.HttpServer;
import crud.Noticia;
import handlers.EscritoresHandler;
import handlers.LeitoresHandler;
import handlers.RootHandler;
import rmi.Servico;
import rmi.ServicoListener;

public class ClienteUm implements ServicoListener{
    private static final int LOGIN = 0;
    private static final int MENU_CONVIDADO = 1;

    public static void main(String[] args) {
        try {
            String nomeServico = "ServidorDeNoticias";
            int porta = 1234;

            ServicoListener ClienteUm = new ClienteUm();
            ServicoListener clienteAdistribuido = (ServicoListener) UnicastRemoteObject.exportObject(ClienteUm, 0);

            Registry registry = LocateRegistry.getRegistry(porta);
            Servico servicoRemoto = (Servico) registry.lookup(nomeServico);
            System.out.println("Devia ter caido aqui");
            servicoRemoto.addListener(clienteAdistribuido);

            startUi(servicoRemoto);

//			servicoRemoto.setX(valor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startUi(Servico servico) {
        int choice = printMenu(LOGIN);
        switch(choice){
            case 1:

                break;
            case 2:
                choice = printMenu(MENU_CONVIDADO);
                switch(choice){
                    case 1:
                        break;
                    case 2:
                        System.out.println("Consultando Noticia");
                        try {
                            servico.getNoticia();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Escolha Invalida");
                        printMenu(MENU_CONVIDADO);
                        break;
                }
                break;
            default:
                System.out.println("Escolha Invalida");
                printMenu(LOGIN);
                break;
        }
    }

    static int printMenu(int opt) {
        System.out.println("################################\n");
        Scanner scanner = new Scanner(System.in);
        String line;
        int choice;
        switch (opt){
            case LOGIN:
                System.out.println("Selecione uma das opcoes:");
                System.out.println("1 - Entrar como usuario autenticado");
                System.out.println("2 - Entrar como convidado");
                line = scanner.nextLine();
                choice = Integer.parseInt(line);
                break;
            case MENU_CONVIDADO:
                System.out.println("Selecione uma das opcoes:");
                System.out.println("1 - Consultar notícias de um tópico por intervalo de datas");
                System.out.println("2 - Consultar a última notícia de um tópico.");
                line = scanner.nextLine();
                choice = Integer.parseInt(line);
                break;
            default:
                choice = -1;
        }
        return choice;
    }

    @Override
    public void noticiaRecebida(Noticia noticia) throws RemoteException {
        System.out.println("Servidor devolveu para Cliente Uma notícia " + noticia.getTexto());
    }
}
