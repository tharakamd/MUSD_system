/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MUSD;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTable;

/**
 *
 * @author tharaka
 */
public class MonthlyReportMS4 extends javax.swing.JFrame {

    
    private int month;
    private int year;
    private String inspectorId;
    private DBLocal db;
    
    /**
     * Creates new form MonthlyReportMS4
     */
    
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
    
    public void addData(){
        
        // creating the array to sotore data
        String array[][] = new String[400][4];
        // initializing the array
        for(int i=0;i<400;i++){
            for(int j=0;j<4;j++){
                array[i][j] = new String();
            }
        }
        
        // reading the instrument types
        ArrayList types = db.selectLocal("equipmentdetails", "name", null);
        
        // itierating instrument types
        Iterator it = types.iterator();
        int row=0;
        while(it.hasNext()){
            // updating data
            String cat = (String)it.next();
            array[row][0] = cat;
            
            // reading the equipments of the current equipment type
            ArrayList equipment = db.selectLocal("equipment", "name", "catagory = '" + cat);
            // iterating the equipments
            Iterator itin = equipment.iterator();
            while(itin.hasNext()){
                
            }
            
        }
    }
    
    
    public MonthlyReportMS4() {
        initComponents();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Type", "Amount", "Unit price", "Total"
            }
        ));
        jScrollPane1.setViewportView(tblMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(383, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MonthlyReportMS4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MonthlyReportMS4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MonthlyReportMS4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MonthlyReportMS4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MonthlyReportMS4().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMain;
    // End of variables declaration//GEN-END:variables
}
