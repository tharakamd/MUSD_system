/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MUSD;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 *
 * @author tharaka
 */
public class MonthlyReportMS2 extends javax.swing.JFrame {

    /**
     * Creates new form MonthlyReportMS2
     */
    public MonthlyReportMS2() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private int month;
    private int year;
    private String inspectorId;
    private JTable table = this.tblMain;
    private DBLocal db;
    
    
    public void init(String inspectorId,int year,String month, DBLocal db ){
        this.month = Support.convertMonthtoNumber(month);
        this.year = year;
        this.inspectorId = "`" +  inspectorId + "`" ;       
        this.db = db;
        this.setTitle("Recieved and Deposit report for the month of " + month + " " + year );
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                addData();
            }
        });
        
    }
    
    
    private void addData(){
       String array[][] = new String[32][11];
       ArrayList list ;
       for(int i=0;i<32;i++){
           for(int j=0;j<11;j++)
               array[i][j] = new String();
       }
       
       String date = String.valueOf(year) + "-" + String.valueOf(month);
       int k=1;
       int totalTarders = 0;
       double totalDeposit = 0;
       for(int i=1;i<=31;i++){
           String tmpDate = date + "-" + String.valueOf(i);
           list = null;
           String tmp;
           list = db.selectLocal(inspectorId, "Date","Date = '" + tmpDate + "'" );
           try{
               array[k-1][0] = (String)list.remove(0);
               
               list = db.selectLocal(inspectorId, "Stamping_Center","Date = '" + tmpDate + "'" );
               array[k-1][1] = (String)list.remove(0);
               
               list = db.selectLocal(inspectorId, "Issued_varification_No_From","Date = '" + tmpDate + "'" );
               array[k-1][2] = (String)list.remove(0);
               
               list = db.selectLocal(inspectorId, "Issued_varification_No_To","Date = '" + tmpDate + "'" );
               array[k-1][2] += " - " + (String)list.remove(0);
               
               list = db.selectLocal(inspectorId, "ms2No","Date = '" + tmpDate + "'" );
               array[k-1][3] =  (String)list.remove(0);
               
               list = db.selectLocal(inspectorId, "Amount","Date = '" + tmpDate + "'" );
               array[k-1][4] = (String)list.remove(0);
               
               list = db.selectLocal(inspectorId, "Date_of_money_deposit","Date = '" + tmpDate + "'" );
               array[k-1][5] = (String)list.remove(0);
               
               list = db.selectLocal(inspectorId, "Branch","Date = '" + tmpDate + "'" );
               array[k-1][6] = (String)list.remove(0); 
               
               list = db.selectLocal(inspectorId, "Bill_No","Date = '" + tmpDate + "'" );
               array[k-1][7] = (String)list.remove(0); 
               
               list = db.selectLocal(inspectorId, "Deposit_Amount","Date = '" + tmpDate + "'" );
               tmp = (String)list.remove(0);
               try{
                   double dep = Double.parseDouble(tmp);
                   totalDeposit += dep;
               }catch(Exception e){
                   
               }
               array[k-1][8] =tmp; 
               
               list = db.selectLocal(inspectorId, "assistant","Date = '" + tmpDate + "'" );
               array[k-1][9] = (String)list.remove(0);; 
               
               list = db.selectLocal(inspectorId, "No_of_Traders","Date = '" + tmpDate + "'" );
               tmp = (String)list.remove(0);
               try{
                   int tra = Integer.parseInt(tmp);
                   totalTarders += tra;
               }catch(Exception e){
                   
               }
               array[k-1][10] = tmp;    
               k++;
           }catch(Exception e){
               
           }
           this.setTitle("Recieved and Deposit report for the month of " + month + " " + year  + " || reading data - " + (i*100)/31 + "%");
           
       }
       this.setTitle("Recieved and Deposit report for the month of " + month + " " + year );
       array[k][10] = String.valueOf(totalTarders);
       array[k][8] = String.valueOf(totalDeposit);
       array[k][7] = "Total";
       
       tblMain.setModel(new javax.swing.table.DefaultTableModel(
            array,
            new String [] {
                "Date", "Location", "W/ME no.", "M/S/2 No.", "Amount", "Date of Deposit", "Bank Branch", "Receipt No", "Total Deposit", "Assistant", "No of Traders"
            }
        ));
       
       
       
       
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMain = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setType(java.awt.Window.Type.UTILITY);

        tblMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Location", "W/ME no.", "M/S/2 No.", "Amount", "Date of Deposit", "Bank Branch", "Receipt No", "Total Deposit", "Assistant", "No of Traders"
            }
        ));
        jScrollPane1.setViewportView(tblMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
      
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("errrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MonthlyReportMS2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMain;
    // End of variables declaration//GEN-END:variables
}
