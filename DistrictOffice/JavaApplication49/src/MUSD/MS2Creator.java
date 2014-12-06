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
       String array[][] = new String[31][11];
       ArrayList list = new ArrayList();
       String date = String.valueOf(year) + "-" + String.valueOf(month);
       for(int i=1;i<=31;i++){
           String tmpDate = date + "-" + String.valueOf(i);
           list = db.selectLocal(inspectorId, "'Date'","Date = '" + tmpDate + "'" );
           if(list !=null){
               array[i-1][0] = (String)list.remove(0);
               
               list = db.selectLocal(inspectorId, "'Details of the circuit stamping'","Date = '" + tmpDate + "'" );
               array[i-1][1] = (String)list.remove(0);
               
               list = db.selectLocal(inspectorId, "'Issued varification No. From'","Date = '" + tmpDate + "'" );
               array[i-1][2] = (String)list.remove(0);
               
               list = db.selectLocal(inspectorId, "'Issued varification No. To'","Date = '" + tmpDate + "'" );
               array[i-1][3] = (String)list.remove(0);
               
               list = db.selectLocal(inspectorId, "'Amount'","Date = '" + tmpDate + "'" );
               array[i-1][4] = (String)list.remove(0);
               
               list = db.selectLocal(inspectorId, "'Date of money deposit'","Date = '" + tmpDate + "'" );
               array[i-1][5] = (String)list.remove(0);
               
               list = db.selectLocal(inspectorId, "'Branch'","Date = '" + tmpDate + "'" );
               array[i-1][6] = (String)list.remove(0); 
               
               list = db.selectLocal(inspectorId, "'Bill No.'","Date = '" + tmpDate + "'" );
               array[i-1][7] = (String)list.remove(0); 
               
            //   list = db.selectLocal(inspectorId, "'Branch'","Date = '" + tmpDate + "'" );
               array[i-1][8] ="#########"; 
               
             //  list = db.selectLocal(inspectorId, "'Branch'","Date = '" + tmpDate + "'" );
               array[i-1][9] = "##########"; 
               
               list = db.selectLocal(inspectorId, "'No. of Traders'","Date = '" + tmpDate + "'" );
               array[i-1][10] = (String)list.remove(0);    
               
           }else
           {
               break;
           }
           
       }
       
       table.setModel(new javax.swing.table.DefaultTableModel(
            array,
            new String [] {
                "Date", "Location", "W/ME no.", "M/S/2 No.", "Amount", "Date of Deposit", "Bank Branch", "Receipt No", "Total Deposit", "Assistant", "No of Traders"
            }
        ));
       
       
       
       
    }
    
}
