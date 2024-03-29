import java.util.HashMap;

public class Parameters {
	
	static HashMap<String, String> get(String query) {

		String[] blocchi = query.split("&");
		HashMap<String, String> parameters = new HashMap<>();
		for (String blocco: blocchi) {
			parameters.put(blocco.replaceAll("=.*",""), blocco.replaceAll(".*=",""));
		}
		
		return parameters;
	}
	
}
