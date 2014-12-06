package MUSD;


import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ivan Udakara
 */
public class MS4form extends javax.swing.JFrame {
    
    /*  create an instance from the database class  */
    DBLocal db ;
    int year;
    int month;
    
    ArrayList<Integer> restrictedList = new ArrayList<Integer>();
    int restricted=0;
    String username;//***********################################

    /**
     * Creates new form MS4form
     */
    public MS4form() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    public void init(DBLocal db, User user, int year, int month){
        this.db = db;
        this.username = user.getId();
        this.year = year;
        this.month = month;
        initTable();
    }
    /*  gets the details from the database table    */
    private void initTable() {
        String array[][] = new String[100][4];
       
        // added by tharaka
        String date = String.valueOf(year) + "-" + String.valueOf(month);
        
        
        
        
            /*  keeps the category list */
            ArrayList<String> catagoryList = new ArrayList<String>();
            /*  adda data to the arraylist "categoryList    "*/
            catagoryList = db.selectLocal("equipmentdetails", "name", null);
            
            Iterator it = catagoryList.iterator();
            int i = 0;
            while (it.hasNext()) {
                /*  sets tmpCat to the catagory list one by one */
                String tmpCat = (String) it.next();
                array[i][0] = tmpCat;
                
                /*  extract "names" of the equipments from the "equipment" table  */
                ArrayList equipList = db.selectLocal("equipment", "name", "catagory = '" + tmpCat + "'");
                Iterator itEqu = equipList.iterator();
                
                
                
                /*  extract "rate" of the equipments from the "equipment" table  */
                ArrayList<String> rate = db.selectLocal("equipment", "rate", "catagory = '" + tmpCat + "'");
                Iterator itAmount = rate.iterator();
                
                i++;
                restrictedList.add(i-1);
                while (itEqu.hasNext()) {
                    //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                    String tmpEqu = (String) itEqu.next();
                    array[i][0] = tmpEqu;
                    
                    /*-----------------------------------*/
                    
                    ArrayList<String> eqId = db.selectLocal("equipment", "id", "name = '" + tmpEqu + "'");
                    String eqIdr = eqId.remove(0);
                    int temp = calAmount(eqIdr);//calculateTotals(tmpEqu);
                    array[i][1] = String.valueOf(temp);
                    
                    String tmpAmount = (String) itAmount.next();
                    array[i][2] = tmpAmount;
                    i++;
                }
                restricted = i-1;
                
                /*  extract "amount"s of "names"(equipments) in "array[i][0]"   */
                //ArrayList<String> amount = db.selectLocal(username, temp, temp)
                
            }

            DefaultTableModel model = new DefaultTableModel(array, new String[]{"Item", "No of Units", "Rate", "Amount"}) {
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };

            jTable1.setModel(model);//////
        

    }
    
    public int calAmount(String eqId){
        ArrayList<String> list = db.selectLocal(username,eqId,"Date between '" + this.year + "-" + this.month + "-" + "00" +  "' and '" + this.year+ "-" + this.month + "-" + "31"+ "'");
       Iterator<String> it  = list.iterator();
       int amount = 0;
       while(it.hasNext()){
           String num = it.next();
           try{
               amount += Integer.parseInt(num);
           }catch(Exception e){
               
           }
       }
       return amount;
    }
    public String calculateTotals(String tmpEquName){
        /*  to keep the equipment id that is to be received    */
        String equID = null;
        /*  integer total of amounts   */
        int intTotal = 0;
        /*  String total of amounts   */
        String stringTotal = null;
        
        int temp = 0;
        
        ArrayList<String> tmpList = db.selectLocal("equipment", "id", "name = '" + tmpEquName + "'");
        System.out.println("id has received successfully[calculateTotal]");
        
        /*  equipment id    */
        equID = tmpList.get(0);
        System.out.println("assigned the \"id\" to \"equID\"");
////        System.out.println(equID);
        
        try{
            ArrayList<String> amounts = db.selectLocal(username, equID, null);
            System.out.println("successfully received the \"amounts\" from \"username\"");
            System.out.println(amounts);
            
            for(int i=0; i<amounts.size(); i++){
                intTotal = intTotal + Integer.parseInt((String)amounts.get(i));
            }
        }
        catch(Exception e){
            System.out.println("no equipments found");
        }
        
        stringTotal = String.valueOf(intTotal);
        return stringTotal;
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
        jTable1 = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("M/S/4");
        setType(java.awt.Window.Type.UTILITY);

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        /*  closes the window   */
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

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
            java.util.logging.Logger.getLogger(MS4form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MS4form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MS4form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MS4form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MS4form().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
