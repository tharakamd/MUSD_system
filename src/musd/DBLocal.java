package musd;

// importing packages
import java.sql.*;







public class DBLocal {
	// global variables for database
	private String JDBC_DRIVER = new String("com.mysql.jdbc.Driver");
	private String DB_URL = new String("jdbc:mysql://localhost/test");
	private Connection conn = null;
	private Statement stmt = null;
	
	
	
	
	public boolean connectLocal(){
		// variables for user
		
		final String userName = "root";
		final String password = "";
		
		// trying to connect to database
		
		try{
			// register jdbc drive
			Class.forName("com.mysql.jdbc.Driver");
			// connecting to database
			conn = DriverManager.getConnection(DB_URL,userName,password);
		}catch(SQLException e){
			// if exception found return false
			return false;
		}catch(ClassNotFoundException e){
			// if exception found return false
						return false;
		}
		
		// if everything went perfect return true
		return true;
	}
			
	

}
