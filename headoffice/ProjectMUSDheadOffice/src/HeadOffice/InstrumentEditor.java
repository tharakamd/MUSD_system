/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeadOffice;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author tharaka
 */
public class InstrumentEditor {

    InstrumentEditWindow window;
    DefaultChecker defaultChecker = new DefaultChecker();
    DBLocal db;
    JTextField txtName;
    JTextField txtRate;
    public InstrumentEditor(InstrumentEditWindow window, DBLocal db) {

        this.window = window;
        this.db = db;

    }

    public void initCombo() {

        ArrayList catagoryList = db.selectLocal("equipmentdetails", "name", null);
        Iterator it = catagoryList.iterator();
        JComboBox cmd = window.getCombobox();
        cmd.removeAll();
        while (it.hasNext()) {
            cmd.addItem((String) it.next());

        }
    }

    public boolean checkInputs() {
        boolean correct = true;
        if (!defaultChecker.isDouble(window.getRate())) {
            correct = false;
        }
        if (window.getId() == null) {
            correct = false;
        }
        if (window.getName() == null) {
            correct = false;
        }

        return correct;
    }
    
    public void editInstrument(){
        try{
            String id = window.getId();
            String name = window.getName();
            String catagory = window.getCatagory();
            String rate = window.getRate();
            this.update(id,name,catagory,rate);
            JOptionPane.showMessageDialog(null,"Edited cuccesfully");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error in data");
        }
    }
    
    public void update(String id, String name, String catagory, String rate){
        String out = new String();
        out = "UPDATE `musd`.`equipment` SET `id` = ";
        out += "'" + id + "'";
        out += ", `name` = '";
        out += name + "', `catagory` = '";
        out += catagory + "', `rate` = '";
        out += rate + "' WHERE `equipment`.`id` = '";
        out += id + "'";
        
        db.executeLocal(out);
    }
    
    
    public void findInstrument(){
        String id = window.getId();
        ArrayList tmpList = new ArrayList();
        tmpList = db.selectLocal("equipment","name","id = \"" + id + "\"");
        if(tmpList==null){
            JOptionPane.showMessageDialog(null,"Recode not found");
        }else{
            initCombo();
            txtName = window.getTxtName();
            txtName.setText((String)tmpList.remove(0));
            
            txtRate = window.getTxtRate();
            tmpList = db.selectLocal("equipment","rate","id = \"" + id + "\"");
            txtRate.setText((String)tmpList.remove(0));
        }
    }

}
