/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeadOffice;

/**
 *
 * @author tharaka
 */
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
		ArrayList<String> result = database.selectLocal("inspectordetails","password","username ='"+id+"'");
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
	
	public DBLocal getDatabase(){
		return this.database;
	}

}
