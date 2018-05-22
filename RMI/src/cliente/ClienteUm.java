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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import crud.Noticia;
import rmi.Servico;
import rmi.ServicoListener;
import rmi.Topico;

public class ClienteUm extends UnicastRemoteObject implements ServicoListener {
    private static final int LOGIN = 0;
    private static final int MENU_CONVIDADO = 1;
    private static Scanner scanner = new Scanner(System.in);

    public ClienteUm() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            String nomeServico = "ServidorDeNoticias";
            int porta = 1234;

            ServicoListener cliente = new ClienteUm();
//            ServicoListener clienteAdistribuido = (ServicoListener) UnicastRemoteObject.exportObject(ClienteUm, 0);

            Registry registry = LocateRegistry.getRegistry(porta);
            Servico servicoRemoto = (Servico) registry.lookup(nomeServico);

            startUi(servicoRemoto, cliente);

//			servicoRemoto.setX(valor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startUi(Servico servico, ServicoListener cliente) throws RemoteException {
        int choice = printMenu(LOGIN);
        switch(choice){
            case 1:
                System.out.println("####################\n");
                System.out.println("Insira o seu usuario:");
                String user = scanner.nextLine();
                if(user.equals("autor")){
                    System.out.println("adicionar new");
                    servico.adicionarNoticia(new Noticia("dasdasd"), "Topico 1");
                }else {
                    System.out.println("Insira o topico:");
                    String t = scanner.nextLine();
                    servico.addListener(cliente, t);
                    System.out.println("Escutando Topicos...");
                    scanner.nextLine();
                }
                break;
            case 2:
                choice = printMenu(MENU_CONVIDADO);
                switch(choice){
                    case 1:
                        System.out.println("####################\n");
                        System.out.println("Insira o Topico:");
                        String topico = scanner.nextLine();
                        System.out.println("Insira a data de inicio no formato dd-MM-yyyy:");
                        String initDate = scanner.nextLine();
                        System.out.println("Insira a data de fim no formato dd-MM-yyyy:");
                        String endDate = scanner.nextLine();

                        LocalDate dateInit = LocalDate.parse(initDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        LocalDateTime init = LocalDateTime.of(dateInit, LocalTime.of(0, 0));

                        LocalDate dateEnd = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        LocalDateTime end = LocalDateTime.of(dateEnd, LocalTime.of(0, 0));


                        System.out.println(servico.getNoticias(init, end, topico));


                        break;
                    case 2:
                        System.out.println("Consultando Noticia");
                        try {
                            Noticia noticia = servico.getUltimaNoticia();
                            System.out.println(noticia);
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
