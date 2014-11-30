package HeadOffice;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class InstrumentAdder {

    InstrumentWindow window;
    DefaultChecker defaultChecker = new DefaultChecker();
    DBLocal db;

    public InstrumentAdder(InstrumentWindow window, DBLocal db) {

        this.window = window;
        this.db = db;

    }

    /*
     * reading values form the database
     * reading the equipmentcategory table
     * loading catagories to the catogory list 
     */
    public void initCombo() {

        ArrayList catagoryList = db.selectLocal("equipmentdetails", "name", null);
        Iterator it = catagoryList.iterator();
        JComboBox cmd = window.getCombobox();
        cmd.removeAll();
        while (it.hasNext()) {
            cmd.addItem((String) it.next());

        }
    }

    /*
     * add instruments to the instrument table
     */
    public void addInstrument() {
        if (checkInputs()) {
            String rate = window.getRate();
            String id = window.getId();
            String catagory = window.getCatagory();
            String name = window.getName();
            // adding the data in to the database
            Recode recode = new Recode(3, name, "name");
            Table table = new Table();
            table.addRecode(recode);
            recode = new Recode(3, catagory, "catagory");
            table.addRecode(recode);
            recode = new Recode(3, id, "id");
            table.addRecode(recode);
            recode = new Recode(2, rate, "rate");
             table.addRecode(recode);
            if (db.addLocal("equipment", table)) {
                JOptionPane.showMessageDialog(null, "Added succesfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error in adding the data");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error in data");
        }
    }

    /*
     * checking for the inputs in the instrument addingWindow
     */
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
    /*
     * this is the function to add new category to the instrumentcategory table
     */

    public void addNewCatagory() {
        String catagory = JOptionPane.showInputDialog(null, "Enter the name for the category");
        Recode recode = new Recode(3, catagory, "name");
        Table table = new Table();
        table.addRecode(recode);
        String id = JOptionPane.showInputDialog(null, "Enter the ID for the category");
        recode = new Recode(3, id, "id");
        table.addRecode(recode);
        if (db.addLocal("equipmentdetails", table)) {
            JOptionPane.showMessageDialog(null, "Added succesfully");
            initCombo();
        } else {
            JOptionPane.showMessageDialog(null, "Error in adding the data");
        }

    }

}
