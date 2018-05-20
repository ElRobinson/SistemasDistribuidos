package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class EscritoresHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange ex) throws IOException {

        File path = new File("src/html", "escritoresLogin.html");
        Headers h = ex.getResponseHeaders();

        h.add("Content-Type", "text/html");

        OutputStream out = ex.getResponseBody();


        RootHandler.respond(ex, path, out);

        out.close();
    }


}
