import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class Main {

	public static void main(String[] args) {

		int porta = 8081;
		HttpServer server;

		try {

			server = HttpServer.create(new InetSocketAddress(porta), 0);
			Server.start(server);
			Server.respond(server);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}