package MUSD;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MS2Creator {

    private int month;
    private int year;
    private String inspectorId;
    private JTable table;
    private DBLocal db;
    public MS2Creator(String inspectorId,int year,String month, JTable table, DBLocal db ){
        this.month = Support.convertMonthtoNumber(month);
        this.year = year;
        this.inspectorId = inspectorId;
        this.table = table;
        this.db = db;
    }
    
    private void addData(){
        DefaultTableModel tblModel = new DefaultTableModel();
        
        /*
        have to write code to find the table related to the inspector id
        */
        // tmp code
        
           String tableName = this.inspectorId;
           ArrayList row = new ArrayList();
           String currentDate = new String();
           for(int date=1;date<32;date++){
               currentDate = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(date);
               ArrayList tmpData;
               
               // getting values
               
               // date
               row.add(currentDate);
               
               // location
               
               tmpData = db.selectLocal(tableName, "	Stamping Center", "Date = \"" + currentDate + "\"");
             
           }
        // tmp code ended
           
        
    }
    
}
