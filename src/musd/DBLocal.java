package musd;

// importing packages
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;







public class DBLocal {
	// global variables for database
	private String JDBC_DRIVER = new String("com.mysql.jdbc.Driver");
	private String DB_URL = new String("jdbc:mysql://localhost/musd");
	private Connection conn = null;
	
	
	
	
	
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
	
	public ArrayList selectLocal(String table, String column, String where){
		// creating the sql statement
		String sql = "select " + column + " from " + table + " where " + where;
		
		// creating objects
		Statement statement = null;
		ResultSet resultSet = null;
		
		// list to store the final result
		ArrayList<String> result = new ArrayList<String>();
		
		try {
			// creating the statement
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			
			// extract data from resultSet
			// iterate while end of the result set
			while(resultSet.next()){
				
				// adding the data in to the array list
				result.add((String)resultSet.getString(column));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				// closing the statement and resultSet
				statement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
			
	

}
