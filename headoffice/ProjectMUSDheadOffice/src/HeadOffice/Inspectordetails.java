/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HeadOffice;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author chathura
 */
public class Inspectordetails extends AbstractTableModel {
    private static final String[] colNames={"RegId","first name","last name","grade","District"};
    private static  ArrayList<Inspector> list;

    public Inspectordetails(ArrayList<Inspector> inlist) {
        list=inlist;
    }

    
    @Override
    public int getRowCount() {
        return list.size();
    }
    
    public String getColumnName(int columnIndex){
        return colNames[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            
            case 0:return list.get(rowIndex).getRegId();
            case 1:return list.get(rowIndex).getFirstname();
            case 2:return list.get(rowIndex).getLastname();
            case 3:return list.get(rowIndex).getGrade();
            case 4:return list.get(rowIndex).getDistrict();
            default:return "error";                  
        }          
                
    }
    
}
