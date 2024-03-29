import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Responder implements HttpHandler {

	HttpServer server;
	
	Responder (HttpServer server) {
		this.server = server;
	}
	
	@Override
    public void handle(HttpExchange httpExchange) throws IOException {
        
		String response;
		String query = httpExchange.getRequestURI().getQuery();
    	HashMap<String, String> parameters = Parameters.get(query);
     
    	if (parameters.get("ops").isEmpty()) {
        	response = "Parametro \"ops\" non trovato: operazione non riconosciuta";
        	respond(response,httpExchange);
        } if (parameters.get("ops").equals("stop")) {
            response = "Server fermato correttamente.";
            respond(response,httpExchange);
            Server.stop(server);
        } if (parameters.get("ops").equals("select")) {
            try {
				response = Db.select(parameters.get("campi"), parameters.get("tabelle"));
			} catch (SQLException e) {
				response = e.getMessage();
			}
            respond(response,httpExchange);
        } if (parameters.get("ops").equals("insert")) {
        	String campi = "'" + parameters.get("nome") + "', to_date('" + parameters.get("data") + "', 'DD-MM-YY')";
        	String tabelle = "presenze";
        	Db.insert(campi,tabelle);
        	response = "Inserisco la presenza di " + parameters.get("nome") + " il " + parameters.get("data");
        	respond(response,httpExchange);
        }  else {
        	response = "Operazione \"" + parameters.get("ops") + "\" non implementata";
        	respond(response,httpExchange);
        }
	}
	
	static void respond (String response, HttpExchange httpExchange) {
		
        try {
    		httpExchange.sendResponseHeaders(200, 0);
	        OutputStream os = httpExchange.getResponseBody();
	        os.write(response.getBytes());
	        os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			response = "risposta non valida";
			respond(response,httpExchange);
		}

		
	}
	
}
