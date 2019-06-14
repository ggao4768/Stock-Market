import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

import java.net.InetSocketAddress;
import java.util.Map;

//javac -cp sqlite-jdbc-3.23.1.jar;  Projectserver.java
//path %path%;C:\Program Files\Java\jdk1.8.0_131\bin

public class Projectserver {

    public static void main(String[] args) throws IOException {
        int port = 8500;
        Database   db =  new  Database("jdbc:sqlite:data.db" );
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        String html = Input.readFile("Project.html");

        String sql = "SELECT * FROM Dbsheet1" ;    
        server.createContext("/stock", new RouteHandler(db,sql));

        String sql1 = "SELECT * FROM Dbsheet2" ;    
        server.createContext("/stockpart2", new RouteHandler(db,sql1));
        
        server.start(); 
        System.out.println("Server is listening on port " + port );
    }    
}