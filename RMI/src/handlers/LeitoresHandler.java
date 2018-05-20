package handlers;

import cliente.ClienteUm;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.file.Files;

public class LeitoresHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange ex) throws IOException {

        String method = ex.getRequestMethod();

        switch (method){
            case "POST":
                String requestBody = convertStreamToString(ex.getRequestBody());
                ClienteUm cliente = new ClienteUm();
                System.out.println(requestBody);

                break;

            case "GET":
                File path = new File("src/html", "leitoresLogin.html");
                Headers h = ex.getResponseHeaders();

                h.add("Content-Type", "text/html");

                OutputStream out = ex.getResponseBody();

                RootHandler.respond(ex, path, out);

                out.close();
                break;
        }


    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
