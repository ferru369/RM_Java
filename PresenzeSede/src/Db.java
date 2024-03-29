import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {

    static String url = "jdbc:oracle:thin:@192.168.56.101:1521/orcl";
    static String user = "dev_db";
    static String password = "dev_db";
   
	static String select (String campi, String tabelle) throws SQLException {
		String query = 	"select " + campi + " from " + tabelle;
		ResultSet resultSet = execute(query);
		StringBuilder output = new StringBuilder();
		int numColumns = resultSet.getMetaData().getColumnCount();
	    for (int i = 1; i <= numColumns; i++) {
	        output.append(resultSet.getMetaData().getColumnName(i)).append("\t");
	    }
	    output.append("\n");
		while (resultSet.next()) {
		    for (int i = 1; i <= numColumns; i++) {
		        output.append(resultSet.getString(i)).append("\t");
		    }
		    output.append("\n");
		}
		return output.toString();
	} 
	
	static ResultSet insert (String valori, String tabella) {
		String query = "insert into " + tabella + " values (" + valori + ")";
		ResultSet resultSet = execute(query); 
		return resultSet;
	}
	
	static ResultSet execute (String query) {
		
		try {
	        Connection connection = DriverManager.getConnection(url, user, password);
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(query);
	        System.out.println(resultSet);
	        return resultSet;
	   	} catch (SQLException e) {
			e.printStackTrace();
		}
		
       return null; 
	}
	
	
	
}
