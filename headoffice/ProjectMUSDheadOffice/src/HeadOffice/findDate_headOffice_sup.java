package HeadOffice;

import javax.swing.*;
import java.util.ArrayList;

public class findDate_headOffice_sup {
    //create an insatance from the DBLocal class
    DBLocal DB = new DBLocal();
    
    /*  keeps the status of the connection  */
    private boolean status = false;
    /*  holds the data of the inspectors that are selected according to the query   */
    private ArrayList<String> inspectorInfo = new ArrayList<String>();
    
    /*  keeps the number of years and the number of months*/
    private int years = 0;
    private int months = 0;
   
    /*  today */
    private int todayYear = 2014;
    private int todayMonth = 7;
    private int todayDate = 19;
    
/*  constructor method  */
    public findDate_headOffice_sup(){
        /*connect to the data base
          if connection successful, status--> true
          if connection not successful, status--> false*/
        status = DB.connectLocal();
    }
    
    public String[][] chceckInfo(int year, int months){
         /*  keeps the details brought by the array list*/
        String details[][] = new String[4][50];
        String details1[][] = new String[50][4];
        
        if(status){//if "status" is true checks for info
            System.out.println("successfully connected to the database");
            
            /*  import the "id" of the inspectors that fits for the condition   */
            inspectorInfo = DB.selectLocal("inspectordetails", "firstname", "'"+(todayYear-year)+"'<=yearofreg");
            System.out.println("succefully extracted the inspectors within the range of years");
            
            if(inspectorInfo != null){//determine weather the information list is empty or not,if not empty, then continue
                System.out.println("inspectorInfo is not null");
                
                //for(int i=0; i<inspectorInfo.size(); i++){//****************************
                    
                    inspectorInfo = DB.selectLocal("inspectordetails", "firstname", "'"+(todayYear-year)+"'<=yearofreg");
                    inspectorInfo.toArray(details[0]);
                    System.out.println("successfully inserted the set of first names into the array");
                    //////////////
                    for(int j=0;j<inspectorInfo.size();j++){
                        System.out.println(details[0][j]);
                    }
                    inspectorInfo = DB.selectLocal("inspectordetails", "regid", "'"+(todayYear-year)+"'<=yearofreg");
                    inspectorInfo.toArray(details[1]);
                    System.out.println("successfully inserted the set of regids into the array");
                    /////////////
                    for(int j=0;j<inspectorInfo.size();j++){
                        System.out.println(details[1][j]);
                    }
                    inspectorInfo = DB.selectLocal("inspectordetails", "yearofreg", "'"+(todayYear-year)+"'<=yearofreg");
                    inspectorInfo.toArray(details[2]);
                    System.out.println("successfully inserted the set of yearofregs into the array");
                    ///////////
                    for(int j=0;j<inspectorInfo.size();j++){
                        System.out.println(details[2][j]);
                    }
                
                //}//***************************************
                
                    /*  transforms "details[4][50]" to "details1[50][4]"*/
                    for(int j=0;j<4;j++){
                        for(int k=0;k<50;k++){
                            details1[k][j] = details[j][k];
                        }
                    }
                    System.out.println("trnsformation successful");
                
            }
            else{//if inspectorInfo is null
                System.out.println("no inspector with in this range");
            }
        }
        else{//if status is false
            System.out.println("connetion failed");
        }
        /*  return the requested data to "findDate_headOffice"  */
        return details1;
    }
    
    /*  calculates the days left to complete the number of "year"s*/
    public void calculateLeftDates(String details1[][]){
        
    }
    
}
