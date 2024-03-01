import com.sun.net.httpserver.HttpServer;

public class Server {
   
    public static void start(HttpServer server) {
    	server.start();   
    	System.out.println("Server avviato correttamente.");
    }
	
	public static void respond(HttpServer server) {
	    server.createContext("/", new Responder(server));
	}
 	
    public static void stop(HttpServer server) {
        if (server != null) {
            server.stop(0);
            System.out.println("Server fermato correttamente.");
        }
    }
}