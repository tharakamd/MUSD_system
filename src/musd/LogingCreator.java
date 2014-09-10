package musd;

import java.util.ArrayList;
import javax.swing.*;

public class LogingCreator {
	
	// creating the database object
	private DBLocal database = new DBLocal();
	
	public LogingCreator(){
		// connect to the database
		database.connectLocal();
		
	}
	
	public boolean checkForUserExisting(String id, String pw){
		// searching for the password
		ArrayList<String> result = database.selectLocal("inspector","password","id ='"+id+"'");
		try{
			// the first index as the password
			String password = result.get(0);
			if(pw.equals(password)){
				return true;
			}else{
				JOptionPane.showMessageDialog(null, "Loging error", "Incorrect id or password", 0);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Loging error", "Incorrect id or password", 0);
		}
		return false;
	}

}
