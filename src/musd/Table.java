package musd;

import java.util.ArrayList;

public class Table {

	ArrayList<Recode> tableTypes = new ArrayList<Recode>();
	
	public void addRecode(Recode recode){
		this.tableTypes.add(recode);
	}
	public ArrayList<Recode> getTable(){
		return this.tableTypes;
	}
}
