package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class RootHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange ex) throws IOException {
        File path = new File("src/html", "index.html");
        Headers h = ex.getResponseHeaders();

        h.add("Content-Type", "text/html");

        OutputStream out = ex.getResponseBody();


        respond(ex, path, out);

        out.close();
    }

    static void respond(HttpExchange ex, File path, OutputStream out) throws IOException {
        if (path.exists()) {
            ex.sendResponseHeaders(200, path.length());
            out.write(Files.readAllBytes(path.toPath()));
        } else {
            System.err.println("File not found: " + path.getAbsolutePath());

            ex.sendResponseHeaders(404, 0);
            out.write("404 File not found.".getBytes());
        }
    }
}
