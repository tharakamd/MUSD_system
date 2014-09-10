package musd;

import java.util.ArrayList;

public class app {

	public static void main(String[] args) {
		DBLocal dbLocal = new DBLocal();
		boolean connected;
		System.out.println("####################################");
		connected = dbLocal.connectLocal();
		if(connected)
			System.out.println("DB connected");
		else
			System.out.println("DB connection failed");
		System.out.println("####################################");
		/*
		ArrayList<String> result = new ArrayList<String>();
		result = dbLocal.selectLocal("inspector","name","district='matara'");
		System.out.println(result.get(2));
		*/
		System.out.println("####################################");
		System.out.println("Checking password scheme");
		LogingCreator lc = new LogingCreator();
		boolean pww = lc.checkForUserExisting("IP0001","123456");
		System.out.println(pww);
		
		// opening the loging window
		LoginWindow loginWindow = new LoginWindow();
		loginWindow.show();
		
	}

}
