import com.sun.net.httpserver.HttpServer;
import handlers.EscritoresHandler;
import handlers.LeitoresHandler;
import handlers.RootHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ClienteManager {
    public static void main(String args[]) throws IOException {
        int port = 9000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("server started at " + port);
        server.createContext("/", new RootHandler());
        server.createContext("/escritores", new EscritoresHandler());
        server.createContext("/leitores", new LeitoresHandler());
        server.setExecutor(null);
        server.start();
    }
}
