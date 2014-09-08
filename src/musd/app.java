package musd;

public class app {

	public static void main(String[] args) {
		DBLocal dbLocal = new DBLocal();
		
		System.out.println(dbLocal.connectLocal());
		
	}

}
