/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MUSD;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author tharaka
 */
public class InstrumentListWindow extends javax.swing.JFrame {

    /**
     * Creates new form InstrumentListWindow
     */
    DBLocal db;

    public InstrumentListWindow() {
        
         try {
            ClassLoader cl = this.getClass().getClassLoader();
            ImageIcon programIcon = new ImageIcon("logo.png");
            setIconImage(programIcon.getImage());
        } catch (Exception whoJackedMyIcon) {
            System.out.println("Could not load program icon.");
        }
        
        
        
        initComponents();
        this.setLocationRelativeTo(null);
        initTable();
    }
    
    public void setDB(DBLocal db){
        this.db = db;
        initTable();
    }

    private void initTable() {
        ArrayList list;
        int num=0;
        String array[][] = new String[400][4];
        for (int i = 0; i < 40; i++) {
            try {
                list = db.selectLocal("equipment", "id", "indexx = '" + i + "'");
                if (list != null) {
                    array[num][0] = (String)list.remove(0);
                    list = db.selectLocal("equipment", "name", "indexx = '" + i + "'");
                    array[num][1] = (String)list.remove(0);
                    list = db.selectLocal("equipment", "catagory", "indexx = '" + i + "'");
                    array[num][2] = (String)list.remove(0);
                    list = db.selectLocal("equipment", "rate", "indexx = '" + i + "'");
                    array[num][3] = (String)list.remove(0);
                    num++;
                }
            } catch (Exception e) {

            }
        }
         tblMain.setModel(new javax.swing.table.DefaultTableModel(
            array,
            new String [] {
                "ID", "Name", "Catagory", "Rate"
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
        setTitle("Instrument List");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        tblMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Catagory", "Rate"
            }
        ));
        jScrollPane1.setViewportView(tblMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addGap(42, 42, 42))
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
            java.util.logging.Logger.getLogger(InstrumentListWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InstrumentListWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InstrumentListWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InstrumentListWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InstrumentListWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMain;
    // End of variables declaration//GEN-END:variables
}
